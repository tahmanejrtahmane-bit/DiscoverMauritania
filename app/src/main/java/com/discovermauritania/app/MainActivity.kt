package com.discovermauritania.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Green = Color(0xFF087A57)
private val Gold = Color(0xFFFFC928)
private val Pale = Color(0xFFF5F8F6)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { DiscoverApp() }
    }
}

@Composable
fun DiscoverApp() {
    var tab by remember { mutableStateOf(0) }
    MaterialTheme(colorScheme = lightColorScheme(primary = Green, secondary = Gold, background = Pale)) {
        Scaffold(
            containerColor = Pale,
            bottomBar = { BottomNav(tab) { tab = it } }
        ) { pad ->
            Box(Modifier.padding(pad)) {
                when (tab) {
                    0 -> HomeScreen()
                    1 -> ExploreScreen()
                    2 -> MapScreen()
                    3 -> FavoritesScreen()
                    else -> ProfileScreen()
                }
            }
        }
    }
}

@Composable
fun TopBar(title: String, back: Boolean = false) {
    Row(Modifier.fillMaxWidth().background(Green).padding(horizontal = 16.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
        if (back) Icon(Icons.Default.ArrowBack, null, tint = Color.White)
        Spacer(Modifier.width(10.dp))
        Text(title, color = Color.White, fontSize = 21.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), textAlign = TextAlign.Right)
        Icon(Icons.Default.Search, null, tint = Color.White)
    }
}

@Composable
fun HomeScreen() {
    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = 24.dp)) {
        item {
            TopBar("🇲🇷  اكتشف موريتانيا")
            HeroCard()
            Text("استكشف موريتانيا", fontSize = 22.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(16.dp))
            CategoryGrid()
            AdCard()
            Text("أماكن مميزة", fontSize = 21.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(16.dp))
        }
        items(listOf("وادي إكليبت", "شاطئ نواذيبو", "قلعة وادان")) { PlaceCard(it) }
    }
}

@Composable
fun HeroCard() {
    Box(Modifier.padding(12.dp).fillMaxWidth().height(210.dp).clip(RoundedCornerShape(22.dp)).background(Brush.verticalGradient(listOf(Color(0xFF4AA8D8), Color(0xFFD88D39))))) {
        Column(Modifier.align(Alignment.BottomEnd).padding(18.dp), horizontalAlignment = Alignment.End) {
            Text("موريتانيا", color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
            Text("أرض التنوع والجمال", color = Color.White, fontSize = 16.sp)
            Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = Green)) { Text("اكتشف الآن") }
        }
    }
}

@Composable
fun CategoryGrid() {
    val cats = listOf("🏜️\nالأماكن السياحية", "🏙️\nالمدن", "🍲\nالمطبخ الموريتاني", "🎭\nالثقافة والتقاليد", "📸\nالصور والفيديوهات", "📍\nخريطة الأماكن")
    Column(Modifier.padding(horizontal = 12.dp)) {
        cats.chunked(3).forEach { row ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                row.forEach { c -> Card(Modifier.weight(1f).padding(vertical = 4.dp), shape = RoundedCornerShape(14.dp)) { Text(c, Modifier.padding(12.dp).fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 13.sp) } }
            }
        }
    }
}

@Composable
fun AdCard() {
    Card(Modifier.padding(12.dp).fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFF0B5FC0)), shape = RoundedCornerShape(16.dp)) {
        Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
            Text("📱", fontSize = 40.sp); Spacer(Modifier.width(10.dp)); Column(Modifier.weight(1f)) { Text("إعلان", color = Color.White, fontWeight = FontWeight.Bold); Text("معدات وأدوات لصناعة المحتوى", color = Color.White) }; Button(onClick = {}) { Text("اعرف المزيد") }
        }
    }
}

@Composable
fun PlaceCard(name: String) {
    Card(Modifier.padding(horizontal = 12.dp, vertical = 5.dp).fillMaxWidth(), shape = RoundedCornerShape(18.dp)) {
        Row(Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(105.dp, 75.dp).clip(RoundedCornerShape(14.dp)).background(Brush.linearGradient(listOf(Color(0xFF4AA8D8), Color(0xFFD9A24C)))), contentAlignment = Alignment.Center) { Text("🏜️", fontSize = 34.sp) }
            Spacer(Modifier.width(12.dp)); Column(Modifier.weight(1f), horizontalAlignment = Alignment.End) { Text(name, fontSize = 18.sp, fontWeight = FontWeight.Bold); Text("موريتانيا", color = Color.Gray); Text("★ 4.8", color = Color(0xFFB07A00)) }
        }
    }
}

@Composable fun ExploreScreen() { Column { TopBar("الأماكن السياحية", true); LazyColumn { item { Text("ابحث عن مكان سياحي... 🔎", Modifier.padding(16.dp), color = Color.Gray) }; items(listOf("وادي إكليبت", "شاطئ نواذيبو", "قلعة وادان", "شنقيط")) { PlaceCard(it) } } } }
@Composable fun MapScreen() { Column { TopBar("خريطة الأماكن", true); Box(Modifier.fillMaxSize().background(Color(0xFFD9E7C8)), contentAlignment = Alignment.Center) { Text("🗺️\nخريطة موريتانيا\n📍 شنقيط   📍 وادان   📍 نواذيبو", textAlign = TextAlign.Center, fontSize = 20.sp) } } }
@Composable fun FavoritesScreen() { Column { TopBar("المفضلة", true); Text("❤️ الأماكن المحفوظة ستظهر هنا", Modifier.fillMaxWidth().padding(40.dp), textAlign = TextAlign.Center, color = Color.Gray) } }
@Composable fun ProfileScreen() { LazyColumn { item { TopBar("ملفي الشخصي"); Column(Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) { Text("👤", fontSize = 64.sp); Text("صانع محتوى موريتاني", fontSize = 21.sp, fontWeight = FontWeight.Bold); Spacer(Modifier.height(20.dp)); EarningsCard(); Button(onClick = {}, Modifier.fillMaxWidth()) { Text("➕ نشر فيديو جديد") }; Button(onClick = {}, Modifier.fillMaxWidth()) { Text("💰 الأرباح والسحب") } } } } }
@Composable fun EarningsCard() { Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp)) { Column(Modifier.padding(18.dp), horizontalAlignment = Alignment.End) { Text("إجمالي الأرباح", color = Color.Gray); Text("$152.47", fontSize = 30.sp, fontWeight = FontWeight.ExtraBold, color = Green); Text("المشاهدات والمبيعات · هذا الشهر") } } }

@Composable
fun BottomNav(selected: Int, onSelect: (Int) -> Unit) {
    NavigationBar(containerColor = Color.White) {
        val items = listOf("الرئيسية" to Icons.Default.Home, "اكتشف" to Icons.Default.Explore, "الخريطة" to Icons.Default.LocationOn, "المفضلة" to Icons.Default.Favorite, "حسابي" to Icons.Default.Person)
        items.forEachIndexed { i, pair -> NavigationBarItem(selected = selected == i, onClick = { onSelect(i) }, icon = { Icon(pair.second, null) }, label = { Text(pair.first, fontSize = 10.sp) }) }
    }
}
