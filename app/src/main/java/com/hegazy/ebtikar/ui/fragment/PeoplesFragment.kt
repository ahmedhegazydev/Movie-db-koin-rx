package com.hegazy.ebtikar.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.hegazy.ebtikar.R
import com.hegazy.ebtikar.adapters.PopularPeoplesAdapter
import com.hegazy.ebtikar.constants.Constants
import com.hegazy.ebtikar.databinding.FragmentPopularPeoplesBinding
import com.hegazy.ebtikar.model.PeopleResponse
import com.hegazy.ebtikar.ui.activity.MainActivity
import com.hegazy.ebtikar.utils.checkInternetConnection
import com.hegazy.ebtikar.utils.doToast
import com.hegazy.ebtikar.utils.setupDialog
import com.hegazy.ebtikar.viewmodel.PopularPeoplesViewModel
import kotlinx.android.synthetic.main.fragment_popular_peoples.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class PeoplesFragment : Fragment(), PopularPeoplesAdapter.PeopleItemClickListener {
    private lateinit var viewDataBinding: FragmentPopularPeoplesBinding
    private lateinit var mAdapterPopularPeople: PopularPeoplesAdapter
    val model by viewModel<PopularPeoplesViewModel>()
    var dialog: AlertDialog? = null
    val gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentPopularPeoplesBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        dialog = setupDialog(requireActivity())


    }

    private fun setupUI() {
        rv_popular.layoutManager = GridLayoutManager(requireActivity(), 3)
        rv_popular?.setHasFixedSize(true)
        mAdapterPopularPeople = PopularPeoplesAdapter(this, requireActivity())
        rv_popular.adapter = mAdapterPopularPeople
        rv_popular?.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                if (rv_popular?.canScrollVertically(1) == false) {
//                    if (!hasNextPage)
//                        return
//                    if (!model.isRequesting.value!!) {
//                        model.isRequesting.value = true
//                        model.findNextPeoples()
//                    }
//                }
            }
        })

        getAllPopularPeoples()

    }

    private fun getAllPopularPeoples() {

        GlobalScope.launch(Dispatchers.Main) {
            checkInternetConnection(requireActivity(),
                action = {
                    model.extractedPeoples.postValue(mutableListOf())
                    Timber.d("getPopularPeoples action")
                    model.isRequesting.value = true
                    model.getPopularPeoples(pageIndex = 1)

                },
                onDisconnected = {
                    Timber.d("getPopularPeoples onDisconnected")
//                    include_no_internet_notifications_fragment.visibility = View.GONE
//                    rv_notifications_fragments.visibility = View.GONE
//                    include_no_internet_notifications_fragment.visibility = View.VISIBLE
                })
        }


        model.extractedPeoples.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                return@Observer
            }
            Timber.d("size %s", it.size)

            mAdapterPopularPeople.setItems(it)


        })

        model.errorSingleLiveEvent.observe(viewLifecycleOwner, Observer {
            doToast(requireContext(), getString(it))

        })

        model.isRequesting.observe(viewLifecycleOwner, Observer {
            if (it) {
                showDialogLoading()
                Timber.d("show-dialog")
            } else {
                hideDialogLoading()
            }
        })

    }

    private fun hideDialogLoading() {
        dialog?.dismiss()
    }

    private fun showDialogLoading() {
        dialog?.show()
    }

    override fun onPeopleClick(
        position: Int,
        item: PeopleResponse.Result,
        viewHolder: PopularPeoplesAdapter.ViewHolder
    ) {

        Timber.d("Person_id = ${item.id}")
        val bundle = Bundle()
        bundle.putString(
            Constants.ARG_KEY_PEOPLE_ITEM,
            gson.toJson(item, PeopleResponse.Result::class.java)
        )
        (requireActivity() as MainActivity).navigateTo(R.id.personDetailsFragment, bundle)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("onDestroyView")


    }

}