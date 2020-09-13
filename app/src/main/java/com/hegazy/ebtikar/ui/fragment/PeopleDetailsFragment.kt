package com.hegazy.ebtikar.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import com.google.gson.Gson
import com.hegazy.ebtikar.adapters.PeopleImagesAdapter
import com.hegazy.ebtikar.constants.Constants
import com.hegazy.ebtikar.databinding.FragmentPeopleDetailsBinding
import com.hegazy.ebtikar.model.DetailsResponse.Profile
import com.hegazy.ebtikar.model.PeopleResponse
import com.hegazy.ebtikar.utils.checkInternetConnection
import com.hegazy.ebtikar.utils.doToast
import com.hegazy.ebtikar.utils.setupDialog
import com.hegazy.ebtikar.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.fragment_people_details.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class PersonDetailsFragment : Fragment(), PeopleImagesAdapter.ImageItemClickListener {
    private lateinit var viewDataBinding: FragmentPeopleDetailsBinding

    //    private var mAdapter: PersonImagesAdapter? = null
    val model by viewModel<DetailsViewModel>()
    val gson = Gson()
    var peopleItem: PeopleResponse.Result? = null
    var dialog: AlertDialog? = null
    private lateinit var mAdapterPersonImages: PeopleImagesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentPeopleDetailsBinding.inflate(inflater, container, false)
        viewDataBinding.peopleDetailsFragment = this
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated PersonDetailsFragment")
        Timber.d("size_dummy = ${Profile.generateDummyData().size}")

        setupUI()

        initDialog()

        getPassedPersonId()

        accessCoverFlow()

        getPeopleImages()

    }

    private fun setupUI() {
        rv_person_images.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.HORIZONTAL, false
        )

        rv_person_images?.setHasFixedSize(true)
        mAdapterPersonImages = PeopleImagesAdapter(this, requireActivity())
        rv_person_images.adapter = mAdapterPersonImages


        val snapHelper = GravitySnapHelper(Gravity.END)
//        val snapHelper = GravitySnapHelper(Gravity.START)
        snapHelper.attachToRecyclerView(rv_person_images)


    }

    private fun accessCoverFlow() {
//        mAdapter = PersonImagesAdapter(requireActivity(), Profile.generateDummyData())
//        mAdapter!!.setData(Profile.generateDummyData())
//        coverflow.adapter = mAdapter
//        coverflow.setOnItemClickListener { parent, view, position, id ->
//        }
//        coverflow.setOnScrollPositionListener(object : OnScrollPositionListener {
//            override fun onScrolledToPosition(position: Int) {
//            }
//            override fun onScrolling() {
//            }
//        })
    }

    private fun initDialog() {
        dialog = setupDialog(requireActivity())
    }

    private fun getPassedPersonId() {
        arguments?.containsKey(Constants.ARG_KEY_PEOPLE_ITEM).apply {
            if (this == true) {
                peopleItem = gson.fromJson(
                    arguments?.getString(Constants.ARG_KEY_PEOPLE_ITEM),
                    PeopleResponse.Result::class.java
                )

            }
        }
    }

    private fun getPeopleImages() {
        GlobalScope.launch(Dispatchers.Main) {
            checkInternetConnection(requireActivity(),
                action = {
                    model.extractedImages.postValue(mutableListOf())
                    Timber.d("getPopularPeoples action")
                    model.isRequesting.value = true
                    model.getPeopleImages(peopleItem?.id)

                },
                onDisconnected = {
                    Timber.d("getPopularPeoples onDisconnected")
                })
        }

        model.extractedImages.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                return@Observer
            }
            Timber.d("extractedImages = size %s", it.size)
//            mAdapter?.setData(it)
            mAdapterPersonImages.setItems(it)

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

    override fun onImageClick(
        position: Int,
        item: Profile,
        viewHolder: PeopleImagesAdapter.ViewHolder
    ) {


    }


}