package com.nispax.studio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val StudioBlack = Color(0xFF090A0C)
private val Panel = Color(0xFF17191D)
private val Titanium = Color(0xFFA6ABB3)
private val StudioRed = Color(0xFFE53945)
private val Muted = Color(0xFF858B95)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                NispaxStudioApp()
            }
        }
    }
}

@Composable
private fun NispaxStudioApp() {
    var screen by rememberSaveable { mutableStateOf("home") }
    var projectName by rememberSaveable { mutableStateOf("Untitled project") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = StudioBlack
    ) {
        if (screen == "editor") {
            EditorScreen(
                projectName = projectName,
                onBack = { screen = "home" }
            )
        } else {
            HomeScreen(
                onCreateProject = {
                    projectName = "Untitled project"
                    screen = "editor"
                },
                onOpenProject = {
                    projectName = it
                    screen = "editor"
                }
            )
        }
    }
}

@Composable
private fun HomeScreen(
    onCreateProject: () -> Unit,
    onOpenProject: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(StudioBlack)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        item {
            Spacer(Modifier.height(28.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "NISPAX",
                        color = Color.White,
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 2.sp
                    )
                    Text(
                        "STUDIO",
                        color = StudioRed,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 5.sp
                    )
                }
                Text("0.1.0", color = Muted, fontSize = 12.sp)
            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Panel, RoundedCornerShape(22.dp))
                    .padding(20.dp)
            ) {
                Text("YOUR CREATIVE WORKSPACE", color = Titanium, fontSize = 11.sp, letterSpacing = 1.5.sp)
                Spacer(Modifier.height(10.dp))
                Text(
                    "Make every frame count.",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "A powerful mobile editing studio is taking shape.",
                    color = Muted,
                    fontSize = 14.sp
                )
                Spacer(Modifier.height(20.dp))
                Button(
                    onClick = onCreateProject,
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = StudioRed)
                ) {
                    Text("+  New project", fontWeight = FontWeight.Bold)
                }
            }
        }

        item {
            Text("RECENT PROJECTS", color = Titanium, fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.5.sp)
        }

        item {
            ProjectCard("Untitled project", "New editing session", onClick = { onOpenProject("Untitled project") })
        }

        item {
            Text("STUDIO TOOLS", color = Titanium, fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.5.sp)
        }

        item {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                ToolCard("Media", "Import footage", Modifier.weight(1f))
                ToolCard("Effects", "Coming soon", Modifier.weight(1f))
            }
        }

        item {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                ToolCard("Audio", "Coming soon", Modifier.weight(1f))
                ToolCard("Export", "Coming soon", Modifier.weight(1f))
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ProjectCard(
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Panel, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(58.dp)
                .background(Color(0xFF292D33), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("▶", color = StudioRed, fontSize = 22.sp)
        }
        Column(modifier = Modifier.padding(start = 13.dp).weight(1f)) {
            Text(title, color = Color.White, fontWeight = FontWeight.SemiBold)
            Text(subtitle, color = Muted, fontSize = 12.sp)
        }
        Text("›", color = Titanium, fontSize = 26.sp)
    }
}

@Composable
private fun ToolCard(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Panel, RoundedCornerShape(16.dp))
            .padding(15.dp)
    ) {
        Text(title, color = Color.White, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(5.dp))
        Text(subtitle, color = Muted, fontSize = 11.sp)
    }
}

@Composable
private fun EditorScreen(
    projectName: String,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(StudioBlack)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "‹",
                color = Color.White,
                fontSize = 32.sp,
                modifier = Modifier.clickable(onClick = onBack).padding(end = 12.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(projectName, color = Color.White, fontWeight = FontWeight.Bold)
                Text("EDITING WORKSPACE", color = Muted, fontSize = 10.sp, letterSpacing = 1.3.sp)
            }
            Text("EXPORT", color = StudioRed, fontWeight = FontWeight.Bold, fontSize = 12.sp)
        }

        Spacer(Modifier.height(18.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFF111317), RoundedCornerShape(18.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("▶", color = StudioRed, fontSize = 42.sp)
                Spacer(Modifier.height(8.dp))
                Text("Preview", color = Titanium, fontWeight = FontWeight.SemiBold)
                Text("Your video preview will appear here", color = Muted, fontSize = 11.sp)
            }
        }

        Spacer(Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("TIMELINE", color = Titanium, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.4.sp)
            Spacer(Modifier.weight(1f))
            Text("00:00 / 00:00", color = Muted, fontSize = 11.sp)
        }
        Spacer(Modifier.height(10.dp))
        TimelineTrack("VIDEO 1", "Add video")
        TimelineTrack("OVERLAY", "Add overlay")
        TimelineTrack("AUDIO", "Add audio")
        Spacer(Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            EditorTool("Media")
            EditorTool("Text")
            EditorTool("Audio")
            EditorTool("Effects")
        }
        Spacer(Modifier.height(12.dp))
    }
}

@Composable
private fun TimelineTrack(label: String, hint: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Panel, RoundedCornerShape(10.dp))
            .padding(horizontal = 12.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, color = Titanium, fontSize = 9.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.8f))
        Box(
            modifier = Modifier
                .weight(2f)
                .background(Color(0xFF252930), RoundedCornerShape(6.dp))
                .padding(10.dp)
        ) {
            Text(hint, color = Muted, fontSize = 10.sp)
        }
    }
}

@Composable
private fun EditorTool(label: String) {
    Box(
        modifier = Modifier
            .weight(1f)
            .background(Panel, RoundedCornerShape(10.dp))
            .padding(vertical = 13.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(label, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Medium)
    }
}
