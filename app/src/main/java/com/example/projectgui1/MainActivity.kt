package com.example.projectgui1

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
import androidx.compose.material.icons.twotone.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Black
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import java.sql.Types.NULL


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
    NavHost(navController = navController, startDestination = "bienvenida"){
        //aqui se declaran las rutas
        //esto de abajo es para la destination route
        composable("bienvenida"){
            Bienvenida(navController = navController)
        }
        //otra ruta
        composable("signup"){
            SignUp(navController = navController)
        }
        composable("login"){
            LogIn(navController = navController)
        }
        composable("sidebar"){
            Sidebar(navController = navController)
        }
        composable("watched"){
            Watched(navController = navController)
        }
        composable("wachlist"){
            Watchlist(navController = navController)
        }
        composable("feedback"){
            FeedBack(navController = navController)
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
            text = "DESCUBRE LAS MEJORES RECOMENDACIONES EN SERIES Y PELÍCULAS",
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
fun SignUp(navController: NavHostController) {
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
                label = { Text("Contraseña") },
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
                        val description = if (hidden) "Ocultar contraseña" else "Revelar contraseña" //6
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
                label = { Text("Confirmar contraseña") },
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
                        val description = if (hidden2) "Ocultar contraseña" else "Revelar contraseña" //6
                        Icon(painter = vector, contentDescription = description)
                    }
                }
            )
        }
        var n=false
        Button(onClick = { navController.navigate("sidebar") },
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
            Text(text = "¿Ya tienes cuenta?  ")
            Text(text = "Inicia sesión", color = Color.White)
        }
    }
}

@Composable
fun LogIn(navController: NavHostController) {
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
                label = { Text("Contraseña") },
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
                        val description = if (hidden) "Ocultar contraseña" else "Revelar contraseña" //6
                        Icon(painter = vector, contentDescription = description)
                    }
                }
            )
            Text(text = "¿Olvidaste tu constraseña?", color = Color.White, modifier = Modifier.padding(start = 85.dp))
        }
        Button(onClick = { navController.navigate("sidebar") },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            modifier = Modifier
                .padding(top = 130.dp)
                .size(width = 250.dp, height = 50.dp),
            shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        ) {
            Text(text = "Iniciar Sesión", color = Color.Black, fontSize = 22.sp)
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
            Text(text = "¿No eres miembro?  ")
            Text(text = "Regístrate", color = Color.White)
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
        SidebarSection(textToRender = "Top películas", imgName = R.drawable.top_icon,navController,"top")
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
            Text(text = "Cerrar sesión", color = DarkYellow, fontSize = 20.sp)
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

//Santiago
data class Peliculas(val titulo:String, val year:Int, val imageId:Int=0)
data class Series(val titulo:String,val year:Int,val imageId:Int=0)
val lPeliculas= arrayListOf(Peliculas("Birds of Prey",2020,R.drawable.peli1))
val lSeries= arrayListOf(Series("No time to die",2021,R.drawable.serie1))
fun agregar_peliculas(){
    lPeliculas.add(Peliculas("Now You see me 2",2016,R.drawable.peli2))
    lPeliculas.add(Peliculas("Onward",2020,R.drawable.peli3))
    lPeliculas.add(Peliculas("Mulan",2020,R.drawable.peli4))

}
@Composable
fun DropDownMenu() {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Series", "Peliculas")
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

}
@Composable
fun Watched(navController: NavHostController) {
    agregar_peliculas()
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
        Row {
            DropDownMenu()
        }
        Row {
            Button(onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                modifier = Modifier
                    .padding(top = 20.dp)
                    .size(width = 120.dp, height = 55.dp),
                shape = RoundedCornerShape(corner = CornerSize(10.dp)),
            ) {
                Text(text = "Agregar", color = Color.White, fontSize = 16.sp)
            }
            val text by remember{ mutableStateOf("") }
            TextField(value =text , onValueChange = {}, modifier = Modifier
                .padding(20.dp)
                .size(width = 200.dp, height = 55.dp))
        }
        Listado_contenido()

    }
}


/////////////////////////////////////////////////////////SANTIAGO wachlist

@Composable
fun Watchlist(navController: NavHostController) {
    agregar_peliculas()
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
            painter = painterResource(R.drawable.wachlist),
            contentDescription = "Wachlist",
            modifier = Modifier
                .size(175.dp)
                .padding(top = 80.dp),
            alignment = Alignment.Center
        )
        Text(
            text = "Wachlist",
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 25.dp)
        )

        Listado_contenido()

    }

}


@Composable
fun Listado_contenido(){

    LazyColumn {
        items(lPeliculas){
                Peli->Componente_visual(Peli)
        }
        items(lSeries){
                Series->
            Componente_visualSeries(Series)
        }
    }
}
@Composable
fun Componente_visual(peli:Peliculas){
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
            Image(
                painter = painterResource(peli.imageId),
                contentDescription = "Wachlist",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.05F),
                contentScale = ContentScale.Crop,
            )


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
                    text = "Año $rank",
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

/////Feedback
@Composable
fun FeedBack(navController: NavHostController) {
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
        Row_stars()
        TextAreaExample()
        Button(onClick = { /*TODO*/ },
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
fun TextAreaExample() {
    var description by remember { mutableStateOf("Comments") }

    TextField(
        value = description,
        onValueChange = { description = it },
        modifier = Modifier
            .padding(top = 85.dp)
            .fillMaxWidth(.7F)
            .fillMaxHeight(0.5F), shape = RoundedCornerShape(corner = CornerSize(30.dp))
    )
}
@Composable
fun Row_stars(){
    Row(modifier=Modifier.padding(top=40.dp)){
        for(x in 1 until 6){
            Star_buttons()
        }
    }
}
@Composable
fun Star_buttons(){
    IconButton(onClick = {

    }) {
        Icon(imageVector = Icons.TwoTone.Star,contentDescription = null, modifier = Modifier.size(100.dp))
    }
}
/////////////////////////////////////////////////////////PILAR
data class Noticias(val titulo:String,val noticia:String,val imageId:Int=0)
val lNoticias = arrayListOf(Noticias("Get ready for a fun", "Cody Fisher", R.drawable.nuno))
fun agregar_recomendacionesSeires(){
    lSeries.add(Series("Star Trek", 1922, R.drawable.startrek))
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

}
@Composable
fun Recomendaciones(navController: NavHostController) {
    agregar_peliculas()
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
        Listado_contenido()

    }

}
//----------------------------------------
@Composable
fun Top(navController: NavHostController) {
    agregar_peliculas()
    IconButton(onClick = { navController.navigate("sidebar") }
    ) {
        Icon(
            Icons.Filled.Menu,contentDescription = "sideBarIcon", modifier = Modifier
                .size(50.dp)
        )
    }
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
        DropDownMenu()
        Listado_contenido()

    }

}
@Composable
fun News(navController: NavHostController) {
    agregar_noticias()
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
        Lista_de_noticias()

    }

}
//-----------------------------------------------
@Composable
fun Lista_de_noticias(){

    LazyColumn {
        items(lNoticias){
                nota->Componente_visual_noticias(nota)
        }
    }
}
@Composable
fun Componente_visual_noticias(nota:Noticias){
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
            Image(
                painter = painterResource(nota.imageId),
                contentDescription = "Recomendaciones",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.05F),
                contentScale = ContentScale.Crop,
            )
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
fun Componente_visualSeries(serie:Series){
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
            Image(
                painter = painterResource(serie.imageId),
                contentDescription = "Recomendaciones",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.05F),
                contentScale = ContentScale.Crop,
            )


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
                    text = "Año $rank",
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