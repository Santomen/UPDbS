package com.example.projectgui1.ui.theme
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectgui1.R

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

data class Peliculas(val titulo:String, val year:Int, val imageId:Int=0)
data class Series(val titulo:String,val year:Int,val imageId:Int=0)
data class Noticias(val titulo:String,val noticia:String,val imageId:Int=0)
class   ContenidoViewModel:ViewModel(){
    val emisorPeliculas = MutableLiveData<List<Peliculas>>()
    val emisorSeries = MutableLiveData<List<Series>>()
    val emisorNoticias = MutableLiveData<List<Noticias>>()
    val lPeliculas= arrayListOf(Peliculas("Birds of Prey",2020,R.drawable.peli1))
    val lSeries= arrayListOf(Series("No time to die",2021,R.drawable.serie1))
    val lNoticias = arrayListOf(Noticias("Get ready for a fun", "Cody Fisher", R.drawable.nuno))
    fun agregar_peliculas(){
        lPeliculas.add(Peliculas("Now You see me 2",2016,R.drawable.peli2))
        lPeliculas.add(Peliculas("Onward",2020,R.drawable.peli3))
        lPeliculas.add(Peliculas("Mulan",2020,R.drawable.peli4))
        emisorPeliculas.postValue(lPeliculas)
    }
    fun agregar_series(){
        lSeries.add(Series("Star Trek", 1922, R.drawable.startrek))
        emisorSeries.postValue(lSeries)
    }
    fun agregar_noticias(){
        lNoticias.add( Noticias("When is the right time to watch series?",
            "Wade Warren",
            R.drawable.ndos))
        lNoticias.add( Noticias("Edible plants",
            "Theresa Webb",
            R.drawable.ncuatro))
        lNoticias.add( Noticias("Look for these places when you don't have a tent",
            "Marvin McKinney",
            R.drawable.ntres))
        lNoticias.add( Noticias("These animals are easy to obtain and consume",
            "Guy Hawkins",
            R.drawable.ncinco))
        lNoticias.add( Noticias("Make a SOS signal from the goods around us",
            "Wade Warren",
            R.drawable.nseis))
        emisorNoticias.postValue(lNoticias)
    }

}