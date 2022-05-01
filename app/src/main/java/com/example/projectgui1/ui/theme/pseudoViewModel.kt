package com.example.projectgui1.ui.theme
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectgui1.APIService
import com.example.projectgui1.Noticias
import com.example.projectgui1.Peliculas
import com.example.projectgui1.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class pseudoViewModel:ViewModel() {
    val emisorEstrellas = MutableLiveData<Int>()
    var estrellas=0
    fun verificar(email:String,password:String):Boolean{
        if(email=="correoprueba@" && password=="contra")
            return true
        else
            return false
    }
    fun add(email:String,password:String,cpassword:String):Boolean{
        if(email!="correoprueba@" && password==cpassword)
            return true
        else
            return false
    }
    fun feed(c:String,n:Int){
        //se agregan al back las estrellas
        println("la persona "+c+" nos ha calificado con "+ n)
        estrellas=n
        emisorEstrellas.postValue(estrellas)
    }
}



class   ContenidoViewModel:ViewModel(){
    val emisorPeliculas = MutableLiveData<List<Peliculas>>()
    val emisorSeries = MutableLiveData<List<Series>>()
    val emisorNoticias = MutableLiveData<List<Noticias>>()
    val lPeliculas= arrayListOf(Peliculas("Birds of Prey",2020,"https://lumiere-a.akamaihd.net/v1/images/image_4f447b1d.jpeg?region=0%2C0%2C540%2C810"))
    val lSeries= arrayListOf(Series("No time to die",2021,"https://lumiere-a.akamaihd.net/v1/images/image_4f447b1d.jpeg?region=0%2C0%2C540%2C810"))
    val lNoticias = arrayListOf(Noticias("Get ready for a fun", "Cody Fisher", "https://lumiere-a.akamaihd.net/v1/images/image_4f447b1d.jpeg?region=0%2C0%2C540%2C810"))
    //Retrodit instance
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("http://192.168.1.124:8080").addConverterFactory(
            GsonConverterFactory.create()).build()
    }

    //la funcion agregar peliculas se encargara de traer del back
    fun agregar_peliculas_w(){
        CoroutineScope(Dispatchers.IO).launch {
            val call= getRetrofit().create(APIService::class.java).getWatchedP()
            val pelis=call.body()
            if(call.isSuccessful){
                //val images = puppies ?: emptyList()
                emisorPeliculas.postValue(pelis)
            }
            else{
                print("No funciona")
            }
        }

    }
    fun agregar_series_w(){
        CoroutineScope(Dispatchers.IO).launch {
            val call= getRetrofit().create(APIService::class.java).getWatchedS()
            val series=call.body()
            if(call.isSuccessful){
                //val images = puppies ?: emptyList()
                emisorSeries.postValue(series)
            }
            else{
                print("No funciona")
            }
        }

    }
    fun agregar_peliculas_s(){
        CoroutineScope(Dispatchers.IO).launch {
            val call= getRetrofit().create(APIService::class.java).getWachlistP()
            val pelis=call.body()
            if(call.isSuccessful){
                //val images = puppies ?: emptyList()
                emisorPeliculas.postValue(pelis)
            }
            else{
                print("No funciona")
            }
        }

    }
    fun agregar_series_s(){
        CoroutineScope(Dispatchers.IO).launch {
            val call= getRetrofit().create(APIService::class.java).getWachlistS()
            val series=call.body()
            if(call.isSuccessful){
                //val images = puppies ?: emptyList()
                emisorSeries.postValue(series)
            }
            else{
                print("No funciona")
            }
        }

    }
    fun agregar_series_top(){
        CoroutineScope(Dispatchers.IO).launch {
            val call= getRetrofit().create(APIService::class.java).getTopS()
            val series=call.body()
            if(call.isSuccessful){
                //val images = puppies ?: emptyList()
                emisorSeries.postValue(series)
            }
            else{
                print("No funciona")
            }
        }

    }
    fun agregar_peliculas_top(){
        CoroutineScope(Dispatchers.IO).launch {
            val call= getRetrofit().create(APIService::class.java).getTopP()
            val series=call.body()
            if(call.isSuccessful){
                //val images = puppies ?: emptyList()
                emisorPeliculas.postValue(series)
            }
            else{
                print("No funciona")
            }
        }

    }
    fun agregar_noticias(){
        lNoticias.add( Noticias("When is the right time to watch series?",
            "Wade Warren",
            "https://lumiere-a.akamaihd.net/v1/images/image_4f447b1d.jpeg?region=0%2C0%2C540%2C810"))
        lNoticias.add( Noticias("Edible plants",
            "Theresa Webb",
            "https://lumiere-a.akamaihd.net/v1/images/image_4f447b1d.jpeg?region=0%2C0%2C540%2C810"))
        lNoticias.add( Noticias("Look for these places when you don't have a tent",
            "Marvin McKinney",
            "https://lumiere-a.akamaihd.net/v1/images/image_4f447b1d.jpeg?region=0%2C0%2C540%2C810"))
        lNoticias.add( Noticias("These animals are easy to obtain and consume",
            "Guy Hawkins",
            "https://lumiere-a.akamaihd.net/v1/images/image_4f447b1d.jpeg?region=0%2C0%2C540%2C810"))
        lNoticias.add( Noticias("Make a SOS signal from the goods around us",
            "Wade Warren",
            "https://lumiere-a.akamaihd.net/v1/images/image_4f447b1d.jpeg?region=0%2C0%2C540%2C810"))
        emisorNoticias.postValue(lNoticias)
    }

}

