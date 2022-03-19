package com.mobilion.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val statusCode: Int?,
    val success: Boolean?,
    val result: LoginResult? = null
)

data class LoginResult(
    val responseCode: Int?,
    val responseMessage: String?,

    @SerializedName("Result")
    @Expose
    val data: ResultData? = null
)

data class ResultData(

    @SerializedName("CardNo")
    @Expose
    val cardNo: String?,

    @SerializedName("Firstname")
    @Expose
    val firstname: String?,

    @SerializedName("Surname")
    @Expose
    val surname: String?,

    @SerializedName("Gender")
    @Expose
    val gender: String?,

    @SerializedName("VehicleNo")
    @Expose
    val vehicleNo1: String?,

    @SerializedName("VehicleNo2")
    @Expose
    val vehicleNo2: String?,

    @SerializedName("VehicleNo3")
    @Expose
    val vehicleNo3: String?,

    @SerializedName("Address")
    @Expose
    val address: String?,

    @SerializedName("BirthDate")
    @Expose
    val birthDate: String?,

    @SerializedName("IsIzinliSms")
    @Expose
    val isIzinliSms: String?,

    @SerializedName("Kvkk")
    @Expose
    val kvkk: String?,

    @SerializedName("City")
    @Expose
    val city: String?,

    @SerializedName("District")
    @Expose
    val district: String?,

    @SerializedName("CityCode")
    @Expose
    val cityCode: String?,

    @SerializedName("DistrictCode")
    @Expose
    val districtCode: String?,

    @SerializedName("GsmTel")
    @Expose
    val gsmTel: String?,

    @SerializedName("Tel")
    @Expose
    val tel: String?,

    @SerializedName("Email")
    @Expose
    val email: String?,

    @SerializedName("AwardBonus")
    @Expose
    val awardBonus: String?,

    @SerializedName("RedeemBonus")
    @Expose
    val redeemBonus: String?,

    @SerializedName("NetBonusTl")
    @Expose
    val netBonusTl: String?,

    @SerializedName("RefCode")
    @Expose
    val refCode: String?,

    @SerializedName("MobilePayment")
    @Expose
    val mobilePayment: String?,

    @SerializedName("Segment")
    @Expose
    val segment: String?
)