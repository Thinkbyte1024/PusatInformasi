package dev.group1.pusatinformasi.presenter

import android.app.ProgressDialog
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.group1.pusatinformasi.model.AuthResponse
import dev.group1.pusatinformasi.network.NetworkConfig
import retrofit2.Call
import retrofit2.Response

class AuthPresenter(val authView: AuthView, val context: Context) {

    private val gson = Gson()

    fun register(nama:String, email:String, password:String, alamat:String){
        val progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Memuat Konten")
        progressDialog.setMessage("Loading ...")
        progressDialog.show()

        NetworkConfig.getService().register(nama, email, password, alamat)
            .enqueue(object : retrofit2.Callback<AuthResponse>{
                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {
                    if (response.isSuccessful){
                        // response success
                        when{
                            response.code() == 200->{
                                authView.onSuccessRegister(response.body()?.message?:"")
                            }
                        }

                    }else{
                        // response bad
                        val type = object : TypeToken<AuthResponse>(){}.type
                        val errorRes: AuthResponse? = gson.fromJson(response.errorBody()!!.charStream(), type)
                        when{
                            response.code() == 400->{
                                authView.onBadRequest(errorRes?.message?:"")
                            }
                            else->{
                                authView.onBadRequest("Internal Server Error")
                            }
                        }
                    }
                    progressDialog.hide()
                    progressDialog.dismiss()
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    authView.onBadRequest(t.message.toString())
                    progressDialog.hide()
                    progressDialog.dismiss()
                }

            })
    }

    fun login(email:String, password: String){
        val progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Memuat Konten")
        progressDialog.setMessage("Loading ...")
        progressDialog.show()

        NetworkConfig.getService()
            .login(email, password)
            .enqueue(object : retrofit2.Callback<AuthResponse>{
                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {
                    if (response.isSuccessful){
                        // response success
                        when{
                            response.code() == 200->{
                                response.body()?.let { authView.onSuccessLogin(it) }
                            }
                        }

                    }else{
                        // response bad
                        val type = object : TypeToken<AuthResponse>(){}.type
                        val errorRes: AuthResponse? = gson.fromJson(response.errorBody()!!.charStream(), type)
                        when{
                            response.code() == 400->{
                                authView.onBadRequest(errorRes?.message?:"")
                            }
                            else->{
                                authView.onBadRequest("Internal Server Error")
                            }
                        }
                    }
                    progressDialog.hide()
                    progressDialog.dismiss()
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    authView.onBadRequest(t.message.toString())
                    progressDialog.hide()
                    progressDialog.dismiss()
                }

            })
    }

}