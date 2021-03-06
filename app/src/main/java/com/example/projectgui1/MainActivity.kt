package com.example.projectgui1

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.twotone.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projectgui1.ui.theme.*
import kotlinx.coroutines.*
import java.net.MalformedURLException
import java.net.URL


val DarkYellow = Color(0xFFF5C518)
val color = Color(0xFF120524)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationComponent()
        }
    }
}
/////Navigation
@Composable
fun NavigationComponent(){
    //Navcontroler, controla el estado de la navegacion de nuestra aplicacion y recuerda en que pantalla estamos
    //navHost trabaja en conjunto con el de arriba y nos dice cual es la pag principal y nos permite de claara las rutas en la app
    val navController= rememberNavController()
    val pseudoUsuarioViewModel: pseudoViewModel = viewModel()
    NavHost(navController = navController, startDestination = "bienvenida"){
        //aqui se declaran las rutas
        //esto de abajo es para la destination route
        composable("bienvenida"){
            Bienvenida(navController = navController)
        }
        //otra ruta
        composable("signup"){
            SignUp(navController = navController,pseudoUsuarioViewModel)
        }
        composable("login"){
            LogIn(navController = navController,pseudoUsuarioViewModel)
        }
        composable("sidebar"){
            Sidebar(navController = navController)
        }
        composable("watched"){
            Watched(navController = navController,pseudoUsuarioViewModel)
        }
        composable("wachlist"){
            Watchlist(navController = navController,pseudoUsuarioViewModel)
        }
        composable("feedback"){
            FeedBack(navController = navController,pseudoUsuarioViewModel)
        }
        composable("top"){
            Top(navController = navController)
        }
        composable("recomendaciones"){
            Recomendaciones(navController = navController)
        }
        composable("news"){
            News(navController = navController)
        }
    }
}
@Composable
fun Sidebar(navController: NavHostController) {
    Column (
        modifier = Modifier
            .fillMaxHeight()
            .width(350.dp)
            .background(color = DarkYellow)
    ) {
        Row (modifier = Modifier.width(350.dp)) {
            Text(text = "UPDb",
                fontSize = 50.sp,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.padding(top = 30.dp, start = 30.dp),
            )
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.size(155.dp)
                .padding(start = 130.dp, top = 45.dp)) {
                Icon(
                    Icons.Filled.Menu,contentDescription = "sideBarIcon", modifier = Modifier.size(50.dp)
                )
            }
            /*Image(
                painter = painterResource(R.drawable.hamburguer_menu),
                contentDescription = "sideBarIcon",
                modifier = Modifier
                    .size(155.dp)
                    .padding(start = 130.dp, top = 45.dp)
                ,
                alignment = Alignment.TopEnd
            )*/
        }
        SidebarSection(textToRender = "Noticias", imgName = R.drawable.news_icon,navController,"news")
        SidebarSection(textToRender = "Top pel??culas", imgName = R.drawable.top_icon,navController,"top")
        SidebarSection(textToRender = "Recomendaciones", imgName = R.drawable.recom_icon,navController,"recomendaciones")
        SidebarSection(textToRender = "Watched", imgName = R.drawable.watched_icon,navController,"watched")
        SidebarSection(textToRender = "WatchList", imgName = R.drawable.watchlist_icon,navController,"wachlist")
        SidebarSection(textToRender = "Feedback", imgName = R.drawable.feedback_icon,navController,"feedback")

        Button(onClick = { navController.navigate("bienvenida") },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
            modifier = Modifier
                .padding(top = 65.dp)
                .size(width = 200.dp, height = 55.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        ) {
            Text(text = "Cerrar sesi??n", color = DarkYellow, fontSize = 20.sp)
        }
    }
}

@Composable
fun SidebarSection(textToRender: String, imgName: Int,navController: NavHostController,ruta:String) {
    Row (modifier = Modifier.height(60.dp)) {
        Image(
            painter = painterResource(imgName),
            contentDescription = "profilePic",
            modifier = Modifier
                .size(60.dp)
                .padding(start = 40.dp)
        )
        TextButton(onClick = { navController.navigate(ruta) }) {
            Text(text = textToRender, fontSize = 20.sp, modifier = Modifier.padding(top = 14.dp, start = 40.dp), color = Color.Black)
        }

    }
    Divider(modifier = Modifier.padding(top = 1.dp), thickness = 2.dp)
}


/////////////////////////////////////////////////////////MIGUEL
@Composable
fun Bienvenida(navController: NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = DarkYellow)
    ) {
        Text(text = "UPDb",
            fontSize = 80.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.padding(top = 100.dp)
        )
        Image(
            painter = painterResource(R.drawable.logo_updb),
            contentDescription = "profilePic",
            modifier = Modifier
                .size(180.dp)
                .padding(top = 60.dp),
            alignment = Alignment.Center
        )
        Text(
            text = "DESCUBRE LAS MEJORES RECOMENDACIONES EN SERIES Y PEL??CULAS",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 75.dp)
        )
        Button(onClick = { navController.navigate("signup") },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
            modifier = Modifier
                .padding(top = 85.dp)
                .size(width = 200.dp, height = 55.dp),
            shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        ) {
            Text(text = "Iniciar", color = DarkYellow, fontSize = 25.sp)
        }
    }
}

@Composable
fun SignUp(navController: NavHostController, pseudoUsuarioViewModel: pseudoViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm_password by remember { mutableStateOf("") }
    var hidden by remember { mutableStateOf(true) }
    var hidden2 by remember { mutableStateOf(true) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = DarkYellow)
    ) {
        Image(
            painter = painterResource(R.drawable.logo_updb),
            contentDescription = "profilePic",
            modifier = Modifier
                .size(175.dp)
                .padding(top = 80.dp),
            alignment = Alignment.Center
        )
        Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 50.dp)){
            TextField(
                modifier = Modifier
                    .size(width = 250.dp, height = 75.dp)
                    .padding(top = 20.dp),
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo") },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = color,
                    backgroundColor = Color.White,
                    focusedLabelColor = color.copy(alpha = ContentAlpha.high),
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = color,
                ),
            )
            TextField(
                modifier = Modifier
                    .size(width = 250.dp, height = 75.dp)
                    .padding(top = 20.dp),
                value = password,
                onValueChange = { password = it },
                label = { Text("Contrase??a") },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = color,
                    backgroundColor = Color.White,
                    focusedLabelColor = color.copy(alpha = ContentAlpha.high),
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = color,
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),//2
                singleLine = true,
                visualTransformation =
                if (hidden) PasswordVisualTransformation() else VisualTransformation.None,//3
                trailingIcon = {// 4
                    IconButton(onClick = { hidden = !hidden }) {
                        val vector = painterResource(//5
                            if (hidden) R.drawable.watched_icon
                            else R.drawable.watched_icon
                        )
                        val description = if (hidden) "Ocultar contrase??a" else "Revelar contrase??a" //6
                        Icon(painter = vector, contentDescription = description)
                    }
                }
            )
            TextField(
                modifier = Modifier
                    .size(width = 250.dp, height = 75.dp)
                    .padding(top = 20.dp),
                value = confirm_password,
                onValueChange = { confirm_password = it },
                label = { Text("Confirmar contrase??a") },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = color,
                    backgroundColor = Color.White,
                    focusedLabelColor = color.copy(alpha = ContentAlpha.high),
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = color,
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),//2
                singleLine = true,
                visualTransformation =
                if (hidden2) PasswordVisualTransformation() else VisualTransformation.None,//3
                trailingIcon = {// 4
                    IconButton(onClick = { hidden2 = !hidden2 }) {
                        val vector = painterResource(//5
                            if (hidden2) R.drawable.watched_icon
                            else R.drawable.watched_icon
                        )
                        val description = if (hidden2) "Ocultar contrase??a" else "Revelar contrase??a" //6
                        Icon(painter = vector, contentDescription = description)
                    }
                }
            )
        }
        //Validacion del usuario
        Button(
            onClick = { enter_application(pseudoUsuarioViewModel.add(email,password,confirm_password),navController = navController) },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            modifier = Modifier
                .padding(top = 100.dp)
                .size(width = 250.dp, height = 50.dp),
            shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        ) {
            Text(text = "Registrarse", color = Color.Black, fontSize = 22.sp)
        }
        Row {
            Text(text = "O", textAlign = TextAlign.Center, color = Color.White, modifier = Modifier.padding(top = 10.dp))
        }
        Button(onClick = { navController.navigate("login") },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            modifier = Modifier
                .padding(top = 10.dp)
                .size(width = 250.dp, height = 50.dp),
            shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        ) {
            Image(
                painter = painterResource(R.drawable.google_icon),
                contentDescription = "profilePic",
                modifier = Modifier
                    .size(30.dp),
                alignment = Alignment.Center
            )
        }
        Row (modifier = Modifier.padding(top = 15.dp)) {
            Text(text = "??Ya tienes cuenta?  ")
            Text(text = "Inicia sesi??n", color = Color.White)
        }
    }
}

@Composable
fun LogIn(navController: NavHostController, pseudoUsuarioViewModel: pseudoViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var hidden by remember { mutableStateOf(true) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = DarkYellow)
    ) {
        Image(
            painter = painterResource(R.drawable.logo_updb),
            contentDescription = "profilePic",
            modifier = Modifier
                .size(175.dp)
                .padding(top = 80.dp),
            alignment = Alignment.Center
        )
        Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 40.dp)) {
            TextField(
                modifier = Modifier
                    .size(width = 250.dp, height = 75.dp)
                    .padding(top = 20.dp),
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo") },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = color,
                    backgroundColor = Color.White,
                    focusedLabelColor = color.copy(alpha = ContentAlpha.high),
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = color,
                ),
            )
            TextField(
                modifier = Modifier
                    .size(width = 250.dp, height = 75.dp)
                    .padding(top = 20.dp),
                value = password,
                onValueChange = { password = it },
                label = { Text("Contrase??a") },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = color,
                    backgroundColor = Color.White,
                    focusedLabelColor = color.copy(alpha = ContentAlpha.high),
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = color,
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),//2
                singleLine = true,
                visualTransformation =
                if (hidden) PasswordVisualTransformation() else VisualTransformation.None,//3
                trailingIcon = {// 4
                    IconButton(onClick = { hidden = !hidden }) {
                        val vector = painterResource(//5
                            if (hidden) R.drawable.watched_icon
                            else R.drawable.watched_icon
                        )
                        val description = if (hidden) "Ocultar contrase??a" else "Revelar contrase??a" //6
                        Icon(painter = vector, contentDescription = description)
                    }
                }
            )
            Text(text = "??Olvidaste tu constrase??a?", color = Color.White, modifier = Modifier.padding(start = 85.dp))
        }
        //validar usuario
        Button(onClick = { enter_application(pseudoUsuarioViewModel.verificar(email,password),navController = navController) },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            modifier = Modifier
                .padding(top = 130.dp)
                .size(width = 250.dp, height = 50.dp),
            shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        ) {
            Text(text = "Iniciar Sesi??n", color = Color.Black, fontSize = 22.sp)
        }
        Row {

            Text(text = "O", textAlign = TextAlign.Center, color = Color.White, modifier = Modifier.padding(top = 10.dp))
        }
        Button(onClick = { navController.navigate("signup") },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            modifier = Modifier
                .padding(top = 10.dp)
                .size(width = 250.dp, height = 50.dp),
            shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        ) {
            Image(
                painter = painterResource(R.drawable.google_icon),
                contentDescription = "profilePic",
                modifier = Modifier
                    .size(30.dp),
                alignment = Alignment.Center
            )
        }
        Row (modifier = Modifier.padding(top = 15.dp)) {
            Text(text = "??No eres miembro?  ")
            Text(text = "Reg??strate", color = Color.White)
        }
    }
}


fun enter_application(valido:Boolean,navController: NavHostController){
    if(valido)
        navController.navigate("sidebar")

}

@Composable
fun Watched(navController: NavHostController, pseudoUsuarioViewModel: pseudoViewModel) {
    val (valueP, setValueP) = remember { mutableStateOf("") }
    IconButton(onClick = { navController.navigate("sidebar") }
    ) {
        Icon(
            Icons.Filled.Menu,contentDescription = "sideBarIcon", modifier = Modifier
                .size(50.dp)
        )
    }
    Column( horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
        Image(
            painter = painterResource(R.drawable.ojo),
            contentDescription = "Wachlist",
            modifier = Modifier
                .size(175.dp)
                .padding(top = 80.dp),
            alignment = Alignment.Center
        )
        Text(
            text = "Wactched",
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 25.dp)
        )
        //Drop down button
        var mostrar by remember { mutableStateOf("") }
        Row {
            val opciones = listOf("Series", "Peliculas")

            mostrar = DropDownMenu(opciones)
        }
        Row {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                modifier = Modifier
                    .padding(top = 20.dp)
                    .size(width = 120.dp, height = 55.dp),
                shape = RoundedCornerShape(corner = CornerSize(10.dp)),
            ) {
                Text(text = "Agregar", color = Color.White, fontSize = 16.sp)
            }
            val text by remember { mutableStateOf("") }
            TextField(
                value = text, onValueChange = {}, modifier = Modifier
                    .padding(20.dp)
                    .size(width = 200.dp, height = 55.dp)
            )
        }
        val contenidoViewModel: ContenidoViewModel = viewModel()
        val lPeliculas = contenidoViewModel.emisorPeliculas.observeAsState()
        val lSeries = contenidoViewModel.emisorSeries.observeAsState()
        if (mostrar == "Peliculas") {
            contenidoViewModel.agregar_peliculas_w()
            lPeliculas.value?.run { //Si el valor de usuariosState value no es nulo ejecutalo
                Listado_contenido_pelis(this)
            }
        } else if (mostrar == "Series") {
            contenidoViewModel.agregar_series_w()
            lSeries.value?.run{
                Listado_contenido_series(this)
            }
        }
    }
}

@Composable
fun Watchlist(navController: NavHostController, pseudoUsuarioViewModel: pseudoViewModel) {
    val (valueP, setValueP) = remember { mutableStateOf("") }
    IconButton(onClick = { navController.navigate("sidebar") }
    ) {
        Icon(
            Icons.Filled.Menu,contentDescription = "sideBarIcon", modifier = Modifier
                .size(50.dp)
        )
    }
    Column( horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
        Image(
            painter = painterResource(R.drawable.wachlist),
            contentDescription = "Wachlist",
            modifier = Modifier
                .size(175.dp)
                .padding(top = 80.dp),
            alignment = Alignment.Center
        )
        Text(
            text = "Wactched",
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 25.dp)
        )
        //Drop down button
        var mostrar by remember { mutableStateOf("") }
        Row {
            val opciones = listOf("Series", "Peliculas")

            mostrar = DropDownMenu(opciones)
        }
        Row {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                modifier = Modifier
                    .padding(top = 20.dp)
                    .size(width = 120.dp, height = 55.dp),
                shape = RoundedCornerShape(corner = CornerSize(10.dp)),
            ) {
                Text(text = "Agregar", color = Color.White, fontSize = 16.sp)
            }
            val text by remember { mutableStateOf("") }
            TextField(
                value = text, onValueChange = {}, modifier = Modifier
                    .padding(20.dp)
                    .size(width = 200.dp, height = 55.dp)
            )
        }
        val contenidoViewModel: ContenidoViewModel = viewModel()
        val lPeliculas = contenidoViewModel.emisorPeliculas.observeAsState()
        val lSeries = contenidoViewModel.emisorSeries.observeAsState()
        if (mostrar == "Peliculas") {
            contenidoViewModel.agregar_peliculas_s()
            lPeliculas.value?.run { //Si el valor de usuariosState value no es nulo ejecutalo
                Listado_contenido_pelis(this)
            }
        } else if (mostrar == "Series") {
            contenidoViewModel.agregar_series_s()
            lSeries.value?.run{
                Listado_contenido_series(this)
            }
        }
    }
}
@Composable
fun FeedBack(navController: NavHostController, pseudoUsuarioViewModel: pseudoViewModel) {
    val N = pseudoUsuarioViewModel.emisorEstrellas.observeAsState()
    val (valueN, setValueN) = remember { mutableStateOf(0) }
    val (valueDescription, setValueDescription) = remember { mutableStateOf("Comments") }
    var ban by remember { mutableStateOf(false) }
    if(ban==false){
        N.value?.run{
            setValueN(this)
        }
        ban=true
    }

    IconButton(onClick = { navController.navigate("sidebar") }

    ) {
        Icon(
            Icons.Filled.Menu,contentDescription = "sideBarIcon", modifier = Modifier
                .size(50.dp)
        )
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier= Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(20.dp)){

        Image(
            painter = painterResource(R.drawable.comments),
            contentDescription = "FeedBack",
            modifier = Modifier
                .size(175.dp)
                .padding(top = 80.dp),
            alignment = Alignment.Center
        )
        Text(
            text = "FEEDBACK",
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 75.dp)
        )
        //Image(painter = painterResource(id = comments  ) , contentDescription ="comments" )
        Row_stars(valueN, setValueN,N)
        textarea(valueDescription,setValueDescription)
        Button(onClick = { pseudoUsuarioViewModel.feed(valueDescription,valueN) },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
            modifier = Modifier
                .padding(top = 85.dp)
                .size(width = 200.dp, height = 55.dp),
            shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        ) {
            Text(text = "Send", color = Color.White, fontSize = 25.sp)
        }
    }

}
@Composable
fun textarea(description: String, setValueDescription: (String) -> Unit) {
    TextField(
        value = description,
        onValueChange = { setValueDescription(it)},
        modifier = Modifier
            .padding(top = 85.dp)
            .fillMaxWidth(.7F)
            .fillMaxHeight(0.5F), shape = RoundedCornerShape(corner = CornerSize(30.dp))
    )

}
@Composable
fun Recomendaciones(navController: NavHostController) {
    IconButton(onClick = { navController.navigate("sidebar") }
    ) {
        Icon(
            Icons.Filled.Menu,contentDescription = "sideBarIcon", modifier = Modifier
                .size(50.dp)
        )
    }
    val contenidoViewModel:ContenidoViewModel=viewModel()
    val lPeliculas = contenidoViewModel.emisorPeliculas.observeAsState()
    val lSeries = contenidoViewModel.emisorSeries.observeAsState()
    Column( horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()){
        Image(
            painter = painterResource(R.drawable.recom_icon),
            contentDescription = "Recomendaciones",
            modifier = Modifier
                .size(175.dp)
                .padding(top = 50.dp),
            alignment = Alignment.Center
        )

        Text(
            text = "Recomendaciones",
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 15.dp)
        )
        contenidoViewModel.agregar_peliculas_w()
        contenidoViewModel.agregar_series_w()
        lPeliculas.value?.run { //Si el valor de usuariosState value no es nulo ejecutalo
            Listado_contenido_pelis(this)
        }
        lSeries.value?.run{
            Listado_contenido_series(this)
        }
    }

}
@Composable
fun Top(navController: NavHostController) {
    IconButton(onClick = { navController.navigate("sidebar") }
    ) {
        Icon(
            Icons.Filled.Menu,contentDescription = "sideBarIcon", modifier = Modifier
                .size(50.dp)
        )
    }
    val contenidoViewModel:ContenidoViewModel=viewModel()
    val lPeliculas = contenidoViewModel.emisorPeliculas.observeAsState()
    val lSeries = contenidoViewModel.emisorSeries.observeAsState()
    /* Image(painter = painterResource(R.drawable.hamburguer_menu),
         contentDescription = "hamburger_menu",
         modifier = Modifier
             .size(50.dp)
             .padding(start = 20.dp, top = 20.dp))*/
    Column( horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()){
        Image(
            painter = painterResource(R.drawable.top_icon),
            contentDescription = "Recomendaciones",
            modifier = Modifier
                .size(100.dp)
                .padding(top = 50.dp),
            alignment = Alignment.Center
        )
        Text(
            text = "Top",
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 15.dp)
        )
        val opciones= listOf<String>("Series","Peliculas")
        var mostrar by remember { mutableStateOf("") }
        mostrar=DropDownMenu(opciones)
        if (mostrar == "Peliculas") {
            contenidoViewModel.agregar_peliculas_top()
            lPeliculas.value?.run { //Si el valor de usuariosState value no es nulo ejecutalo
                Listado_contenido_pelis(this)
            }
        } else if (mostrar == "Series") {
            contenidoViewModel.agregar_series_top()
            lSeries.value?.run{
                Listado_contenido_series(this)
            }
        }

    }

}

@Composable
fun News(navController: NavHostController) {
    IconButton(onClick = { navController.navigate("sidebar") }
    ) {
        Icon(
            Icons.Filled.Menu,contentDescription = "sideBarIcon", modifier = Modifier
                .size(50.dp)
        )
    }
    Column( horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()){
        Image(
            painter = painterResource(R.drawable.news_icon),
            contentDescription = "News",
            modifier = Modifier
                .size(150.dp)
                .padding(top = 50.dp),
            alignment = Alignment.Center
        )
        Text(
            text = "News",
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 15.dp)
        )
        val contenidoViewModel:ContenidoViewModel=viewModel()
        val lNoticias=contenidoViewModel.emisorNoticias.observeAsState()
        contenidoViewModel.agregar_noticias()
        lNoticias.value?.run{ //Si el valor de usuariosState value no es nulo ejecutalo
            Lista_de_noticias(this)
        }
    }

}
//-----------------------------------------------
//Componentes
@Composable
fun Componente_visualSeries(serie:Series){
    val bitmap=remember{mutableStateOf<ImageBitmap?>(null)}
    val scope= rememberCoroutineScope()
    val url=try{
        URL(serie.imageId)
    }catch(e: MalformedURLException){null}
    url?.loadImage(scope,bitmap)
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
            )
            .fillMaxWidth(0.5F),
        elevation = 8.dp,
    ) {

        Column {
            bitmap.value?.apply{
                Image(
                    bitmap=this, contentDescription = "Recomendaciones"
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
            ){
                Text(
                    text = serie.titulo,
                    modifier = Modifier
                        .fillMaxWidth(0.5F)
                        .wrapContentWidth(Alignment.Start)
                    ,
                    style = MaterialTheme.typography.h5
                )
                val rank =serie.year.toString()
                Text(
                    text = "A??o $rank",
                    modifier = Modifier
                        .fillMaxWidth(1F)
                        .wrapContentWidth(Alignment.End)
                    ,
                    style = MaterialTheme.typography.h6
                )

            }
        }
    }
}
@Composable
fun Componente_visual_noticias(nota: Noticias){
    val bitmap=remember{mutableStateOf<ImageBitmap?>(null)}
    val scope= rememberCoroutineScope()
    val url=try{
        URL(nota.imageId)
    }catch(e: MalformedURLException){null}
    url?.loadImage(scope,bitmap)
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
            )
            .fillMaxWidth(0.5F),
        elevation = 8.dp,
    ) {


        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
        ){
            bitmap.value?.apply{
                Image(
                    bitmap=this, contentDescription = "Wachlist"
                )
            }
            Text(
                text = nota.titulo,
                modifier = Modifier
                    .fillMaxWidth(2F)
                    .wrapContentWidth(Alignment.Start)
                    .padding(bottom = 5.dp),
                style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold
            )
            Text(
                text = nota.noticia,
                modifier = Modifier
                    .fillMaxWidth(1F)
                    .wrapContentWidth(Alignment.Start),
                style = MaterialTheme.typography.h6
            )


        }

    }
}
@Composable
fun Lista_de_noticias(noticias: List<Noticias>) {
    LazyColumn {
        items(noticias){
                nota->Componente_visual_noticias(nota)
        }
    }
}

@Composable
fun Row_stars(valueN: Int, setValueN: (Int) -> Unit, N: State<Int?>) {

    Row(modifier=Modifier.padding(top=40.dp)){
        for(x in 1 until 6){
            var i=false
            if(x<=valueN)
                i=true
            Star_buttons(x,setValueN,i,N)
        }
    }
}
@Composable
fun Star_buttons(x: Int, setValueN: (Int) -> Unit, i: Boolean, N: State<Int?>) {
    IconButton(onClick = {
        setValueN(x)
    }) {
        if(i==true)
           Icon(imageVector = Icons.Filled.Star,contentDescription = null, modifier = Modifier.size(100.dp))
        else
            Icon(imageVector = Icons.TwoTone.Star,contentDescription = null, modifier = Modifier.size(100.dp))
    }

}
@Composable
fun Listado_contenido_pelis(list: List<Peliculas>) {

    LazyColumn {
        items(list){
                Peli->Componente_visualPeliculas(Peli)
        }
    }

}
@Composable
fun Listado_contenido_series(list: List<Series>) {
    LazyColumn {
        items(list){
                Series->
            Componente_visualSeries(Series)
        }
    }
}
@Composable
fun Componente_visualPeliculas(peli: Peliculas){
    val bitmap=remember{mutableStateOf<ImageBitmap?>(null)}
    val scope= rememberCoroutineScope()
    val url=try{
        URL(peli.imageId)
    }catch(e: MalformedURLException){null}
    url?.loadImage(scope,bitmap)
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
            )
            .fillMaxWidth(0.5F),
        elevation = 8.dp,
    ) {

        Column {
            bitmap.value?.apply{
                Image(
                    bitmap=this, contentDescription = "Wachlist"
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
            ){
                Text(
                    text = peli.titulo,
                    modifier = Modifier
                        .fillMaxWidth(0.5F)
                        .wrapContentWidth(Alignment.Start)
                    ,
                    style = MaterialTheme.typography.h5
                )
                val rank =peli.year.toString()
                Text(
                    text = "A??o $rank",
                    modifier = Modifier
                        .fillMaxWidth(1F)
                        .wrapContentWidth(Alignment.End)
                    ,
                    style = MaterialTheme.typography.h6
                )

            }
        }
    }
}
@Composable
fun DropDownMenu(opciones: List<String>):String {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = opciones
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column(Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = {Text("Seleccion el tipo de contenido")},
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textfieldSize.width.toDp()})
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }
    return selectedText
}
fun URL.loadImage(scope: CoroutineScope, bitmap:MutableState<ImageBitmap?>){
    val result : Deferred<ImageBitmap?> = scope.async(Dispatchers.IO){
        BitmapFactory.decodeStream(openStream()).asImageBitmap()
    }
    scope.launch{
        bitmap.value=result.await()
    }
}