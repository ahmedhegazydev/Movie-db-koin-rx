package com.hegazy.ebtikar.repo.remote.retrofit

/**
 * Created by shehab on 11/2/2019.
 */

object ApiUrls {

    val SOCKET_URL = NetworkConstants.getBaseUrl() + "socket"
    val SUPPORT_SOCKET_URL = NetworkConstants.getBaseUrl() + "chat"
    const val COMPLETE_PROFILE = "identity/api/2.0/Account/Driver/Profile"
    const val FAQ = "common/api/1.0/Faq/driver/faqs"
    const val DRIVER_STATUS = "ordering/api/1.0/Drivers/status"
    const val IS_DRIVER_ACTIVE = "commonquery/api/1/CommonQuery/Driver/IsActive"
    const val UPDATE_FCM_TOKEN = "identity/api/2.0/Account/FcmToken"
    const val REFRESH_TOKEN = "identity/api/2.0/Account/RefreshToken"
    const val CHANGE_LANGUAGE = "identity/api/2.0/Account/Language"
    const val LOGOUT = "identity/api/2.0/Account/Logout"
    const val LOGIN = "identity/api/2.0/Account/login"
    const val COMPLAINS = "complaint/api/1.0/Complaints"
    const val SUGGEST = "complaint/api/1.0/Proposals"
    const val ORDER_STATUS = "ordering/api/1.0/Orders/State"
    const val DRIVER_STATE = "ordering/api/1/Orders/Driver/State"

    const val BALANCE = "nwallet/api/1/NWallet"
    const val USER_ACTIVATION_STATUS = "identity/api/2.0/Account/GetUserActivationStatus"
    const val REGIONS = "location/api/1.0/Area/utilities"
    const val ORDERS_IDS = "ordering/api/1/Orders/GetOrdersIds"
    const val GOOGLE_MAPS_DIRECTIONS_API = "https://maps.googleapis.com/maps/api/"
    const val MY_ORDERS = "commonquery/api/1/CommonQuery/Driver/myOrderPaginated"
    const val PROFILE = "identity/api/2.0/Account/profile"
    const val CANCELLATION_REASONS = "ordering/api/1.0/CancellationReason/CancelationReasons"
    const val RATE = "rating/api/1/OrderRating/orderrate"
    const val CHAT = "ordering/api/1.1/Chat/GetChats"
    const val ORDERS_PER_YEAR = "ordering/api/1/Orders/ListOrdersPerYear"
    const val ORDERS_PER_WEEK = "ordering/api/1/Orders/ListOrdersPerWeek"
    const val ORDERS_PER_DAY = "ordering/api/1/Orders/ListOrdersPerDay"
    const val ORDER_ID = "orderId"

    //    const val ORDER_DETAILS = "ordering/api/1/Orders/GetOrder"
    const val OLD_ORDER_DETAILS = "commonquery/api/1/CommonQuery/Order/GetOrder"
    const val ORDER_DETAILS = DRIVER_STATE

    const val BANK_ACCOUNT = "payments/api/1/BankAccount"
    const val GET_BANK_ACCOUNT = "payments/api/1/BankAccount/page"

    //    const val GET_BANK_ACCOUNT = "payments/api/1/BankAccount/GetByUser"
    const val GET_BANKS_LIST = "payments/api/1/Bank/All"
    const val GET_COMPLAIN_REASONS = "complaint/api/1/ComplaintTypes/GetAllLight"
    const val UPDATE_PERSONAL_PROFILE = "identity/api/2.0/Account/driver/profile"
    const val SET_BANK_ACCOUNT_PRIMARY = "payments/api/1.0/BankAccount/setAsPrimary"
    const val DELETE_BANK_ACCOUNT = "payments/api/1.0/BankAccount"
    const val SET_USER_PASSWORD = "identity/api/2.0/Account/profile/SetUserPassword"
    const val VALIDATE_PHONE_NUMBER = "identity/api/2.0/Account/profile/validatephoneNumber"
    const val CHECK_PHONE_NUMBER_WITH_PASSWORD =
        "identity/api/2.0/Account/profile/CheckPhoneNumberWithPassword"
    const val HAS_PASSWORD = "identity/api/2.0/Account/profile/HasPassword"
    const val CHANGE_PASSWORD = "identity/api/2.0/Account/profile/ChangeUserPassword"
    const val LOGIN_WITH_PASSWORD = "identity/api/2.0/Account/LoginWithPassword"
    const val CHANGE_PHONE_NUMBER = "identity/api/2.0/Account/profile/phoneNumber"
    const val FORGET_PASSWORD = "identity/api/2.0/Account/profile/ForgetUserPassword"
    const val ORDERS_HIT_MAP = "ordering/api/1/Orders/OrdersHitMap"
    const val GET_COMPENSATION_QUESTIONS = "complaint/api/1/CompensationQuestion/GetAll"
    const val GET_MY_ORDERS_TODAY = "commonquery/Api/1/CommonQuery/Driver/myOrderToday"
    const val GET_AVAILABLE_CURRENCIES = "payments/api/1/Currency/GetActive"
    const val GET_COMPENSATION_LIST = "complaint/api/1/Compensation/GetPage"
    const val GET_COMPENSATION_POLICIES = "complaint/api/1/CompensationPolicy/GetAll"
    const val GET_COMPENSATION_DETAILS = "/complaint/api/1/Compensation/{id}/"
    const val CREATE_COMPENSATION = "/complaint/api/1/Compensation/Create"
    const val GENERAL_CONFIGURATION = "/common/api/1/GeneralConfiguration/GeneralConfiguration"
    const val RUNNING_ORDERS = "commonquery/api/1/CommonQuery/Driver/CurrentOrders"
    const val ORDER_QUEUE = "ordering/api/1/Orders/OrderQueue"
    const val NEW_RUNNING_ORDERS = "ordering/api/1/Orders/Driver/CurrentOrders"
    const val CLAIM_REQUEST_TYPE = "payments/api/1/PaymentSetting/ClaimRequestType"


}
