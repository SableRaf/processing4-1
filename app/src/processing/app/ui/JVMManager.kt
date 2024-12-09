package processing.app.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.github.alexzhirkevich.compottie.*
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.processing.app.generated.resources.Res
import processing.app.Base
import processing.app.Language
import processing.app.Platform
import java.awt.BorderLayout
import java.awt.Desktop
import java.io.File
import java.net.URI
import java.nio.charset.Charset
import javax.swing.SwingUtilities


fun addJDKManger(parent: javax.swing.Box){
    SwingUtilities.invokeLater {
        val composePanel = ComposePanel().apply {
            setContent {
                JDKManager()
            }
        }
        parent.apply {
            add(composePanel, BorderLayout.EAST)
        }
    }

}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
//        JDKManager()
    }
}

// If the user does not have a valid JDK installed on their system, this function will display a message and a button to download the JDK
// Temporary workaround until we ship Processing with a JDK again
@OptIn(ExperimentalResourceApi::class)
@Composable
fun JDKManager(){
    val home = Platform.getJavaHome()
    val valid = File(home, "bin/java").exists()
    if(valid) return

    val color = Theme.getColor("status.notice.bgcolor")
    val colorText = Theme.getColor("status.notice.fgcolor")
    val buttonColor = Theme.getColor("toolbar.button.enabled.field")
    val buttonTextColor = Theme.getColor("toolbar.button.enabled.glyph")

    val fontFamily = FontFamily(
        Font(
            resource = "font/ProcessingSansPro-Regular.ttf",
            weight = FontWeight.W400,
            style = FontStyle.Normal
        )
    )

    val os = System.getProperty("os.name")
    val arch = System.getProperty("os.arch")
    val platform = when {
        os.contains("Windows") -> "windows"
        os.contains("Mac") -> "mac"
        else -> "linux"
    }

    val composition by rememberLottieComposition {
        LottieCompositionSpec.JsonString(
"{\"v\":\"5.3.4\",\"fr\":24,\"ip\":0,\"op\":55,\"w\":1000,\"h\":1000,\"nm\":\"Comp 1\",\"ddd\":0,\"assets\":[{\"id\":\"comp_0\",\"layers\":[{\"ddd\":0,\"ind\":1,\"ty\":0,\"nm\":\"roj\",\"refId\":\"comp_1\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":1,\"k\":[{\"i\":{\"x\":[0.833],\"y\":[0.833]},\"o\":{\"x\":[0.167],\"y\":[0.167]},\"n\":[\"0p833_0p833_0p167_0p167\"],\"t\":6,\"s\":[0],\"e\":[84]},{\"t\":23}],\"ix\":10},\"p\":{\"a\":0,\"k\":[500,500,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[500,500,0],\"ix\":1},\"s\":{\"a\":1,\"k\":[{\"i\":{\"x\":[0.833,0.833,0.833],\"y\":[0.833,0.833,0.833]},\"o\":{\"x\":[0.167,0.167,0.167],\"y\":[0.167,0.167,0.167]},\"n\":[\"0p833_0p833_0p167_0p167\",\"0p833_0p833_0p167_0p167\",\"0p833_0p833_0p167_0p167\"],\"t\":6,\"s\":[100,100,100],\"e\":[157,157,100]},{\"t\":10}],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":6,\"op\":36,\"st\":6,\"bm\":0},{\"ddd\":0,\"ind\":2,\"ty\":0,\"nm\":\"Ama\",\"refId\":\"comp_3\",\"sr\":1,\"ks\":{\"o\":{\"a\":1,\"k\":[{\"i\":{\"x\":[0.833],\"y\":[0.833]},\"o\":{\"x\":[0.167],\"y\":[0.167]},\"n\":[\"0p833_0p833_0p167_0p167\"],\"t\":20,\"s\":[100],\"e\":[1]},{\"t\":29}],\"ix\":11},\"r\":{\"a\":1,\"k\":[{\"i\":{\"x\":[0.833],\"y\":[0.833]},\"o\":{\"x\":[0.167],\"y\":[0.167]},\"n\":[\"0p833_0p833_0p167_0p167\"],\"t\":4,\"s\":[0],\"e\":[85]},{\"t\":29}],\"ix\":10},\"p\":{\"a\":0,\"k\":[497.25,498.5,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[496.75,500.5,0],\"ix\":1},\"s\":{\"a\":1,\"k\":[{\"i\":{\"x\":[0.833,0.833,0.833],\"y\":[0.833,0.833,0.833]},\"o\":{\"x\":[0.167,0.167,0.167],\"y\":[0.167,0.167,0.167]},\"n\":[\"0p833_0p833_0p167_0p167\",\"0p833_0p833_0p167_0p167\",\"0p833_0p833_0p167_0p167\"],\"t\":4,\"s\":[54,54,100],\"e\":[100,100,100]},{\"i\":{\"x\":[0.833,0.833,0.833],\"y\":[0.833,0.833,0.833]},\"o\":{\"x\":[0.167,0.167,0.167],\"y\":[0.167,0.167,0.167]},\"n\":[\"0p833_0p833_0p167_0p167\",\"0p833_0p833_0p167_0p167\",\"0p833_0p833_0p167_0p167\"],\"t\":8,\"s\":[100,100,100],\"e\":[134,134,100]},{\"i\":{\"x\":[0.833,0.833,0.833],\"y\":[0.833,0.833,0.833]},\"o\":{\"x\":[0.167,0.167,0.167],\"y\":[0.167,0.167,0.167]},\"n\":[\"0p833_0p833_0p167_0p167\",\"0p833_0p833_0p167_0p167\",\"0p833_0p833_0p167_0p167\"],\"t\":17,\"s\":[134,134,100],\"e\":[96,96,100]},{\"t\":19}],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":4,\"op\":34,\"st\":4,\"bm\":0},{\"ddd\":0,\"ind\":3,\"ty\":0,\"nm\":\"nar\",\"refId\":\"comp_5\",\"sr\":1,\"ks\":{\"o\":{\"a\":1,\"k\":[{\"i\":{\"x\":[0.833],\"y\":[0.833]},\"o\":{\"x\":[0.167],\"y\":[0.167]},\"n\":[\"0p833_0p833_0p167_0p167\"],\"t\":15,\"s\":[100],\"e\":[0]},{\"t\":20}],\"ix\":11},\"r\":{\"a\":1,\"k\":[{\"i\":{\"x\":[0.833],\"y\":[0.833]},\"o\":{\"x\":[0.167],\"y\":[0.167]},\"n\":[\"0p833_0p833_0p167_0p167\"],\"t\":5,\"s\":[0],\"e\":[46]},{\"t\":21}],\"ix\":10},\"p\":{\"a\":0,\"k\":[497.5,499.25,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[497.5,499.25,0],\"ix\":1},\"s\":{\"a\":1,\"k\":[{\"i\":{\"x\":[0.833,0.833,0.833],\"y\":[0.833,0.833,0.833]},\"o\":{\"x\":[0.167,0.167,0.167],\"y\":[0.167,0.167,0.167]},\"n\":[\"0p833_0p833_0p167_0p167\",\"0p833_0p833_0p167_0p167\",\"0p833_0p833_0p167_0p167\"],\"t\":1,\"s\":[30,30,100],\"e\":[100,100,100]},{\"i\":{\"x\":[0.833,0.833,0.833],\"y\":[0.833,0.833,0.833]},\"o\":{\"x\":[0.167,0.167,0.167],\"y\":[0.167,0.167,0.167]},\"n\":[\"0p833_0p833_0p167_0p167\",\"0p833_0p833_0p167_0p167\",\"0p833_0p833_0p167_0p167\"],\"t\":5,\"s\":[100,100,100],\"e\":[123,123,100]},{\"i\":{\"x\":[0.833,0.833,0.833],\"y\":[0.833,0.833,0.833]},\"o\":{\"x\":[0.167,0.167,0.167],\"y\":[0.167,0.167,0.167]},\"n\":[\"0p833_0p833_0p167_0p167\",\"0p833_0p833_0p167_0p167\",\"0p833_0p833_0p167_0p167\"],\"t\":8,\"s\":[123,123,100],\"e\":[273.393,273.393,100]},{\"t\":14}],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0}]},{\"id\":\"comp_1\",\"layers\":[{\"ddd\":0,\"ind\":1,\"ty\":0,\"nm\":\"ROJO\",\"refId\":\"comp_2\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":-301.855,\"ix\":10},\"p\":{\"a\":0,\"k\":[497.875,499.375,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[496.944,499.56,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0},{\"ddd\":0,\"ind\":2,\"ty\":0,\"nm\":\"ROJO\",\"refId\":\"comp_2\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":-252.88,\"ix\":10},\"p\":{\"a\":0,\"k\":[497.875,499.375,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[496.944,499.56,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0},{\"ddd\":0,\"ind\":3,\"ty\":0,\"nm\":\"ROJO\",\"refId\":\"comp_2\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":-198.233,\"ix\":10},\"p\":{\"a\":0,\"k\":[497.875,499.375,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[496.944,499.56,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0},{\"ddd\":0,\"ind\":4,\"ty\":0,\"nm\":\"ROJO\",\"refId\":\"comp_2\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":-149.663,\"ix\":10},\"p\":{\"a\":0,\"k\":[497.875,499.375,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[496.944,499.56,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0},{\"ddd\":0,\"ind\":5,\"ty\":0,\"nm\":\"ROJO\",\"refId\":\"comp_2\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":-99.267,\"ix\":10},\"p\":{\"a\":0,\"k\":[497.875,499.375,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[496.944,499.56,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0},{\"ddd\":0,\"ind\":6,\"ty\":0,\"nm\":\"ROJO\",\"refId\":\"comp_2\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":-45.613,\"ix\":10},\"p\":{\"a\":0,\"k\":[497.875,499.375,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[496.944,499.56,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0},{\"ddd\":0,\"ind\":7,\"ty\":0,\"nm\":\"ROJO\",\"refId\":\"comp_2\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":10.106,\"ix\":10},\"p\":{\"a\":0,\"k\":[497.875,499.375,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[496.944,499.56,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0}]},{\"id\":\"comp_2\",\"layers\":[{\"ddd\":0,\"ind\":1,\"ty\":4,\"nm\":\"Shape Layer 1\",\"sr\":1,\"ks\":{\"o\":{\"a\":1,\"k\":[{\"i\":{\"x\":[0.833],\"y\":[0.833]},\"o\":{\"x\":[0.167],\"y\":[0.167]},\"n\":[\"0p833_0p833_0p167_0p167\"],\"t\":11,\"s\":[100],\"e\":[0]},{\"t\":14}],\"ix\":11},\"r\":{\"a\":0,\"k\":0,\"ix\":10},\"p\":{\"a\":0,\"k\":[500,500,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[0,0,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"shapes\":[{\"ty\":\"gr\",\"it\":[{\"ind\":0,\"ty\":\"sh\",\"ix\":1,\"ks\":{\"a\":1,\"k\":[{\"i\":{\"x\":0.833,\"y\":0.833},\"o\":{\"x\":0.167,\"y\":0.167},\"n\":\"0p833_0p833_0p167_0p167\",\"t\":1,\"s\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[0.75,-5.625],[-3.625,0],[3.375,-0.75]],\"c\":true}],\"e\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[5.125,-10.125],[-3.625,0],[9.875,-1.75]],\"c\":true}]},{\"i\":{\"x\":0.833,\"y\":0.833},\"o\":{\"x\":0.167,\"y\":0.167},\"n\":\"0p833_0p833_0p167_0p167\",\"t\":3,\"s\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[5.125,-10.125],[-3.625,0],[9.875,-1.75]],\"c\":true}],\"e\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[19.625,-19.625],[5.875,-4.5],[25.375,-7.75]],\"c\":true}]},{\"i\":{\"x\":0.833,\"y\":0.833},\"o\":{\"x\":0.167,\"y\":0.167},\"n\":\"0p833_0p833_0p167_0p167\",\"t\":5.412,\"s\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[19.625,-19.625],[5.875,-4.5],[25.375,-7.75]],\"c\":true}],\"e\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[67.125,-43.625],[30.875,-18.5],[71.375,-36.25]],\"c\":true}]},{\"i\":{\"x\":0.833,\"y\":0.833},\"o\":{\"x\":0.167,\"y\":0.167},\"n\":\"0p833_0p833_0p167_0p167\",\"t\":7.529,\"s\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[67.125,-43.625],[30.875,-18.5],[71.375,-36.25]],\"c\":true}],\"e\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[135.625,-79.625],[73.875,-42],[136.375,-77.25]],\"c\":true}]},{\"i\":{\"x\":0.833,\"y\":0.833},\"o\":{\"x\":0.167,\"y\":0.167},\"n\":\"0p833_0p833_0p167_0p167\",\"t\":11,\"s\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[135.625,-79.625],[73.875,-42],[136.375,-77.25]],\"c\":true}],\"e\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[219.125,-134.125],[194.125,-119.75],[219.375,-133.5]],\"c\":true}]},{\"t\":14}],\"ix\":2},\"nm\":\"Path 1\",\"mn\":\"ADBE Vector Shape - Group\",\"hd\":false},{\"ty\":\"st\",\"c\":{\"a\":0,\"k\":[0,0,0,1],\"ix\":3},\"o\":{\"a\":0,\"k\":100,\"ix\":4},\"w\":{\"a\":0,\"k\":0,\"ix\":5},\"lc\":1,\"lj\":1,\"ml\":4,\"ml2\":{\"a\":0,\"k\":4,\"ix\":8},\"nm\":\"Stroke 1\",\"mn\":\"ADBE Vector Graphic - Stroke\",\"hd\":false},{\"ty\":\"fl\",\"c\":{\"a\":0,\"k\":[0.125670728496,0.339342573577,0.65306372549,1],\"ix\":4},\"o\":{\"a\":0,\"k\":100,\"ix\":5},\"r\":1,\"nm\":\"Fill 1\",\"mn\":\"ADBE Vector Graphic - Fill\",\"hd\":false},{\"ty\":\"tr\",\"p\":{\"a\":0,\"k\":[0,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[0,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100],\"ix\":3},\"r\":{\"a\":0,\"k\":0,\"ix\":6},\"o\":{\"a\":0,\"k\":100,\"ix\":7},\"sk\":{\"a\":0,\"k\":0,\"ix\":4},\"sa\":{\"a\":0,\"k\":0,\"ix\":5},\"nm\":\"Transformar\"}],\"nm\":\"Shape 1\",\"np\":3,\"cix\":2,\"ix\":1,\"mn\":\"ADBE Vector Group\",\"hd\":false}],\"ip\":0,\"op\":30,\"st\":0,\"bm\":0}]},{\"id\":\"comp_3\",\"layers\":[{\"ddd\":0,\"ind\":1,\"ty\":0,\"nm\":\"AMARILLO\",\"refId\":\"comp_4\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":298.463,\"ix\":10},\"p\":{\"a\":0,\"k\":[497.75,500.5,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[497.731,499.593,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0},{\"ddd\":0,\"ind\":2,\"ty\":0,\"nm\":\"AMARILLO\",\"refId\":\"comp_4\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":257.603,\"ix\":10},\"p\":{\"a\":0,\"k\":[497.75,500.5,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[497.731,499.593,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0},{\"ddd\":0,\"ind\":3,\"ty\":0,\"nm\":\"AMARILLO\",\"refId\":\"comp_4\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":211.954,\"ix\":10},\"p\":{\"a\":0,\"k\":[497.75,500.5,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[497.731,499.593,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0},{\"ddd\":0,\"ind\":4,\"ty\":0,\"nm\":\"AMARILLO\",\"refId\":\"comp_4\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":166.163,\"ix\":10},\"p\":{\"a\":0,\"k\":[497.75,500.5,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[497.731,499.593,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0},{\"ddd\":0,\"ind\":5,\"ty\":0,\"nm\":\"AMARILLO\",\"refId\":\"comp_4\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":119.533,\"ix\":10},\"p\":{\"a\":0,\"k\":[497.75,500.5,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[497.731,499.593,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0},{\"ddd\":0,\"ind\":6,\"ty\":0,\"nm\":\"AMARILLO\",\"refId\":\"comp_4\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":69.838,\"ix\":10},\"p\":{\"a\":0,\"k\":[497.75,500.5,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[497.731,499.593,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0},{\"ddd\":0,\"ind\":7,\"ty\":0,\"nm\":\"AMARILLO\",\"refId\":\"comp_4\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":24.35,\"ix\":10},\"p\":{\"a\":0,\"k\":[497.75,500.5,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[497.731,499.593,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0},{\"ddd\":0,\"ind\":8,\"ty\":0,\"nm\":\"AMARILLO\",\"refId\":\"comp_4\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":-22.692,\"ix\":10},\"p\":{\"a\":0,\"k\":[497.75,500.5,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[497.731,499.593,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0}]},{\"id\":\"comp_4\",\"layers\":[{\"ddd\":0,\"ind\":1,\"ty\":4,\"nm\":\"Shape Layer 1\",\"sr\":1,\"ks\":{\"o\":{\"a\":1,\"k\":[{\"i\":{\"x\":[0.833],\"y\":[0.833]},\"o\":{\"x\":[0.167],\"y\":[0.167]},\"n\":[\"0p833_0p833_0p167_0p167\"],\"t\":13,\"s\":[100],\"e\":[0]},{\"t\":16}],\"ix\":11},\"r\":{\"a\":0,\"k\":0,\"ix\":10},\"p\":{\"a\":0,\"k\":[496.25,499.875,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[-3.75,-0.125,0],\"ix\":1},\"s\":{\"a\":1,\"k\":[{\"i\":{\"x\":[0.833,0.833,0.833],\"y\":[0.833,0.833,0.833]},\"o\":{\"x\":[0.167,0.167,0.167],\"y\":[0.167,0.167,0.167]},\"n\":[\"0p833_0p833_0p167_0p167\",\"0p833_0p833_0p167_0p167\",\"0p833_0p833_0p167_0p167\"],\"t\":2,\"s\":[229,229,100],\"e\":[176,176,100]},{\"t\":15}],\"ix\":6}},\"ao\":0,\"shapes\":[{\"ty\":\"gr\",\"it\":[{\"ind\":0,\"ty\":\"sh\",\"ix\":1,\"ks\":{\"a\":1,\"k\":[{\"i\":{\"x\":0.833,\"y\":0.833},\"o\":{\"x\":0.167,\"y\":0.167},\"n\":\"0p833_0p833_0p167_0p167\",\"t\":3,\"s\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[0.75,-5.625],[-3.625,0],[3.375,-0.75]],\"c\":true}],\"e\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[5.125,-10.125],[-3.625,0],[9.875,-1.75]],\"c\":true}]},{\"i\":{\"x\":0.833,\"y\":0.833},\"o\":{\"x\":0.167,\"y\":0.167},\"n\":\"0p833_0p833_0p167_0p167\",\"t\":5,\"s\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[5.125,-10.125],[-3.625,0],[9.875,-1.75]],\"c\":true}],\"e\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[19.625,-19.625],[5.875,-4.5],[25.375,-7.75]],\"c\":true}]},{\"i\":{\"x\":0.833,\"y\":0.833},\"o\":{\"x\":0.167,\"y\":0.167},\"n\":\"0p833_0p833_0p167_0p167\",\"t\":7.412,\"s\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[19.625,-19.625],[5.875,-4.5],[25.375,-7.75]],\"c\":true}],\"e\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[67.125,-43.625],[30.875,-18.5],[71.375,-36.25]],\"c\":true}]},{\"i\":{\"x\":0.833,\"y\":0.833},\"o\":{\"x\":0.167,\"y\":0.167},\"n\":\"0p833_0p833_0p167_0p167\",\"t\":9.529,\"s\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[67.125,-43.625],[30.875,-18.5],[71.375,-36.25]],\"c\":true}],\"e\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[135.625,-79.625],[73.875,-42],[136.375,-77.25]],\"c\":true}]},{\"i\":{\"x\":0.833,\"y\":0.833},\"o\":{\"x\":0.167,\"y\":0.167},\"n\":\"0p833_0p833_0p167_0p167\",\"t\":13,\"s\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[135.625,-79.625],[73.875,-42],[136.375,-77.25]],\"c\":true}],\"e\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[219.125,-134.125],[194.125,-119.75],[219.375,-133.5]],\"c\":true}]},{\"t\":16}],\"ix\":2},\"nm\":\"Path 1\",\"mn\":\"ADBE Vector Shape - Group\",\"hd\":false},{\"ty\":\"st\",\"c\":{\"a\":0,\"k\":[0,0,0,1],\"ix\":3},\"o\":{\"a\":0,\"k\":100,\"ix\":4},\"w\":{\"a\":0,\"k\":0,\"ix\":5},\"lc\":1,\"lj\":1,\"ml\":4,\"ml2\":{\"a\":0,\"k\":4,\"ix\":8},\"nm\":\"Stroke 1\",\"mn\":\"ADBE Vector Graphic - Stroke\",\"hd\":false},{\"ty\":\"fl\",\"c\":{\"a\":0,\"k\":[0.985922181373,0.777212404737,0.310502085966,1],\"ix\":4},\"o\":{\"a\":0,\"k\":100,\"ix\":5},\"r\":1,\"nm\":\"Fill 1\",\"mn\":\"ADBE Vector Graphic - Fill\",\"hd\":false},{\"ty\":\"tr\",\"p\":{\"a\":0,\"k\":[0,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[0,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100],\"ix\":3},\"r\":{\"a\":0,\"k\":0,\"ix\":6},\"o\":{\"a\":0,\"k\":100,\"ix\":7},\"sk\":{\"a\":0,\"k\":0,\"ix\":4},\"sa\":{\"a\":0,\"k\":0,\"ix\":5},\"nm\":\"Transformar\"}],\"nm\":\"Shape 1\",\"np\":3,\"cix\":2,\"ix\":1,\"mn\":\"ADBE Vector Group\",\"hd\":false}],\"ip\":0,\"op\":30,\"st\":0,\"bm\":0}]},{\"id\":\"comp_5\",\"layers\":[{\"ddd\":0,\"ind\":1,\"ty\":0,\"nm\":\"NARANJA\",\"refId\":\"comp_6\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":-329.986,\"ix\":10},\"p\":{\"a\":0,\"k\":[498.25,499.75,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[498.25,499.75,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0},{\"ddd\":0,\"ind\":2,\"ty\":0,\"nm\":\"NARANJA\",\"refId\":\"comp_6\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":-296.86,\"ix\":10},\"p\":{\"a\":0,\"k\":[498.25,499.75,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[498.25,499.75,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0},{\"ddd\":0,\"ind\":3,\"ty\":0,\"nm\":\"NARANJA\",\"refId\":\"comp_6\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":-266.081,\"ix\":10},\"p\":{\"a\":0,\"k\":[498.25,499.75,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[498.25,499.75,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0},{\"ddd\":0,\"ind\":4,\"ty\":0,\"nm\":\"NARANJA\",\"refId\":\"comp_6\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":-236.454,\"ix\":10},\"p\":{\"a\":0,\"k\":[498.25,499.75,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[498.25,499.75,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0},{\"ddd\":0,\"ind\":5,\"ty\":0,\"nm\":\"NARANJA\",\"refId\":\"comp_6\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":-208.651,\"ix\":10},\"p\":{\"a\":0,\"k\":[498.25,499.75,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[498.25,499.75,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0},{\"ddd\":0,\"ind\":6,\"ty\":0,\"nm\":\"NARANJA\",\"refId\":\"comp_6\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":-180.777,\"ix\":10},\"p\":{\"a\":0,\"k\":[498.25,499.75,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[498.25,499.75,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0},{\"ddd\":0,\"ind\":7,\"ty\":0,\"nm\":\"NARANJA\",\"refId\":\"comp_6\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":-153.807,\"ix\":10},\"p\":{\"a\":0,\"k\":[498.25,499.75,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[498.25,499.75,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0},{\"ddd\":0,\"ind\":8,\"ty\":0,\"nm\":\"NARANJA\",\"refId\":\"comp_6\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":-124.081,\"ix\":10},\"p\":{\"a\":0,\"k\":[498.25,499.75,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[498.25,499.75,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0},{\"ddd\":0,\"ind\":9,\"ty\":0,\"nm\":\"NARANJA\",\"refId\":\"comp_6\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":-95.258,\"ix\":10},\"p\":{\"a\":0,\"k\":[498.25,499.75,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[498.25,499.75,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0},{\"ddd\":0,\"ind\":10,\"ty\":0,\"nm\":\"NARANJA\",\"refId\":\"comp_6\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":-64.449,\"ix\":10},\"p\":{\"a\":0,\"k\":[498.25,499.75,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[498.25,499.75,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0},{\"ddd\":0,\"ind\":11,\"ty\":0,\"nm\":\"NARANJA\",\"refId\":\"comp_6\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":-31.972,\"ix\":10},\"p\":{\"a\":0,\"k\":[498.25,499.75,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[498.25,499.75,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0},{\"ddd\":0,\"ind\":12,\"ty\":0,\"nm\":\"NARANJA\",\"refId\":\"comp_6\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":0,\"ix\":10},\"p\":{\"a\":0,\"k\":[498.25,499.75,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[498.25,499.75,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":30,\"st\":0,\"bm\":0}]},{\"id\":\"comp_6\",\"layers\":[{\"ddd\":0,\"ind\":1,\"ty\":4,\"nm\":\"Shape Layer 1\",\"sr\":1,\"ks\":{\"o\":{\"a\":1,\"k\":[{\"i\":{\"x\":[0.833],\"y\":[0.833]},\"o\":{\"x\":[0.167],\"y\":[0.167]},\"n\":[\"0p833_0p833_0p167_0p167\"],\"t\":19,\"s\":[100],\"e\":[0]},{\"t\":24}],\"ix\":11},\"r\":{\"a\":0,\"k\":0,\"ix\":10},\"p\":{\"a\":0,\"k\":[500,500,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[0,0,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"shapes\":[{\"ty\":\"gr\",\"it\":[{\"ind\":0,\"ty\":\"sh\",\"ix\":1,\"ks\":{\"a\":1,\"k\":[{\"i\":{\"x\":0.833,\"y\":0.833},\"o\":{\"x\":0.167,\"y\":0.167},\"n\":\"0p833_0p833_0p167_0p167\",\"t\":2,\"s\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[5.125,-10.125],[-3.625,0],[9.875,-1.75]],\"c\":true}],\"e\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[19.625,-19.625],[5.875,-4.5],[25.375,-7.75]],\"c\":true}]},{\"i\":{\"x\":0.833,\"y\":0.833},\"o\":{\"x\":0.167,\"y\":0.167},\"n\":\"0p833_0p833_0p167_0p167\",\"t\":7,\"s\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[19.625,-19.625],[5.875,-4.5],[25.375,-7.75]],\"c\":true}],\"e\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[67.125,-43.625],[30.875,-18.5],[71.375,-36.25]],\"c\":true}]},{\"i\":{\"x\":0.833,\"y\":0.833},\"o\":{\"x\":0.167,\"y\":0.167},\"n\":\"0p833_0p833_0p167_0p167\",\"t\":12,\"s\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[67.125,-43.625],[30.875,-18.5],[71.375,-36.25]],\"c\":true}],\"e\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[135.625,-79.625],[73.875,-42],[136.375,-77.25]],\"c\":true}]},{\"i\":{\"x\":0.833,\"y\":0.833},\"o\":{\"x\":0.167,\"y\":0.167},\"n\":\"0p833_0p833_0p167_0p167\",\"t\":19,\"s\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[135.625,-79.625],[73.875,-42],[136.375,-77.25]],\"c\":true}],\"e\":[{\"i\":[[0,0],[0,0],[0,0]],\"o\":[[0,0],[0,0],[0,0]],\"v\":[[219.125,-134.125],[194.125,-119.75],[219.375,-133.5]],\"c\":true}]},{\"t\":25}],\"ix\":2},\"nm\":\"Path 1\",\"mn\":\"ADBE Vector Shape - Group\",\"hd\":false},{\"ty\":\"st\",\"c\":{\"a\":0,\"k\":[0,0,0,1],\"ix\":3},\"o\":{\"a\":0,\"k\":100,\"ix\":4},\"w\":{\"a\":0,\"k\":0,\"ix\":5},\"lc\":1,\"lj\":1,\"ml\":4,\"ml2\":{\"a\":0,\"k\":4,\"ix\":8},\"nm\":\"Stroke 1\",\"mn\":\"ADBE Vector Graphic - Stroke\",\"hd\":false},{\"ty\":\"fl\",\"c\":{\"a\":0,\"k\":[0.151382551006,0.753048406863,0.606834680894,1],\"ix\":4},\"o\":{\"a\":0,\"k\":100,\"ix\":5},\"r\":1,\"nm\":\"Fill 1\",\"mn\":\"ADBE Vector Graphic - Fill\",\"hd\":false},{\"ty\":\"tr\",\"p\":{\"a\":0,\"k\":[0,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[0,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100],\"ix\":3},\"r\":{\"a\":0,\"k\":0,\"ix\":6},\"o\":{\"a\":0,\"k\":100,\"ix\":7},\"sk\":{\"a\":0,\"k\":0,\"ix\":4},\"sa\":{\"a\":0,\"k\":0,\"ix\":5},\"nm\":\"Transformar\"}],\"nm\":\"Shape 1\",\"np\":3,\"cix\":2,\"ix\":1,\"mn\":\"ADBE Vector Group\",\"hd\":false}],\"ip\":0,\"op\":30,\"st\":0,\"bm\":0}]}],\"layers\":[{\"ddd\":0,\"ind\":1,\"ty\":0,\"nm\":\"1\",\"refId\":\"comp_0\",\"sr\":1.15,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":0,\"ix\":10},\"p\":{\"a\":0,\"k\":[672,664,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[500,500,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[76,76,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":25,\"op\":66.4,\"st\":25,\"bm\":0},{\"ddd\":0,\"ind\":2,\"ty\":0,\"nm\":\"1\",\"refId\":\"comp_0\",\"sr\":1.15,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":0,\"ix\":10},\"p\":{\"a\":0,\"k\":[332,676,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[500,500,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[76,76,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":13,\"op\":54.4,\"st\":13,\"bm\":0},{\"ddd\":0,\"ind\":3,\"ty\":0,\"nm\":\"1\",\"refId\":\"comp_0\",\"sr\":1.15,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":0,\"ix\":10},\"p\":{\"a\":0,\"k\":[280,288,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[500,500,0],\"ix\":1},\"s\":{\"a\":1,\"k\":[{\"i\":{\"x\":[0.833,0.833,0.833],\"y\":[0.833,0.833,0.833]},\"o\":{\"x\":[0.167,0.167,0.167],\"y\":[0.167,0.167,0.167]},\"n\":[\"0p833_0p833_0p167_0p167\",\"0p833_0p833_0p167_0p167\",\"0p833_0p833_0p167_0p167\"],\"t\":28.2,\"s\":[132.4,132.4,100],\"e\":[61.4,61.4,100]},{\"t\":39.7004882881608}],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":19,\"op\":60.4,\"st\":19,\"bm\":0},{\"ddd\":0,\"ind\":4,\"ty\":0,\"nm\":\"1\",\"refId\":\"comp_0\",\"sr\":1.15,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":0,\"ix\":10},\"p\":{\"a\":0,\"k\":[668,304,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[500,500,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[72.4,72.4,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":5,\"op\":46.4,\"st\":5,\"bm\":0},{\"ddd\":0,\"ind\":5,\"ty\":0,\"nm\":\"1\",\"refId\":\"comp_0\",\"sr\":1.15,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":0,\"ix\":10},\"p\":{\"a\":0,\"k\":[497.438,499.5,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[497.438,499.5,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":11,\"op\":52.4,\"st\":11,\"bm\":0},{\"ddd\":0,\"ind\":6,\"ty\":0,\"nm\":\"1\",\"refId\":\"comp_0\",\"sr\":1.15,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":0,\"ix\":10},\"p\":{\"a\":0,\"k\":[497.438,499.5,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[497.438,499.5,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":31,\"op\":72.4,\"st\":31,\"bm\":0},{\"ddd\":0,\"ind\":7,\"ty\":0,\"nm\":\"1\",\"refId\":\"comp_0\",\"sr\":1.15,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":0,\"k\":0,\"ix\":10},\"p\":{\"a\":0,\"k\":[497.438,499.5,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[497.438,499.5,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"w\":1000,\"h\":1000,\"ip\":0,\"op\":41.4,\"st\":0,\"bm\":0}],\"markers\":[]}"
        )
    }
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = Compottie.IterateForever,
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(color.rgb)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp, 8.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberLottiePainter(
                    composition = composition,
                    progress = { progress },
                ),
                contentDescription = "Lottie animation"
            )
            Text(
                "JDK not found. Please download the JDK to run Processing.",
                fontFamily = fontFamily,
                modifier = Modifier.align(Alignment.CenterVertically),
                color = Color(colorText.rgb)
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(

                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .clickable{
                        openWebpage(URI("https://api.adoptium.net/v3/installer/latest/17/ga/${platform}/${arch}/jre/hotspot/normal/eclipse?project=jdk"))
                    }
                    .background(color = Color(buttonColor.rgb))
                    .padding(16.dp, 8.dp)

                ,
            ) {
                Text(
                    text = "Download JDK",
                    color = Color(buttonTextColor.rgb),
                    fontFamily = fontFamily
                )
            }
        }
    }
}

fun openWebpage(uri: URI?): Boolean {
    val desktop = Desktop.getDesktop() ?: return false
    if (desktop.isSupported(Desktop.Action.BROWSE)) {
        try {
            desktop.browse(uri)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return false
}