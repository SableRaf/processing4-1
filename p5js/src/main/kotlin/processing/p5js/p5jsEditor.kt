package processing.p5js

import processing.app.Base
import processing.app.Formatter
import processing.app.Mode
import processing.app.ui.Editor
import processing.app.ui.EditorState
import processing.app.ui.EditorToolbar
import javax.swing.JMenu

class p5jsEditor(base: Base, path: String?, state: EditorState?, mode: Mode?): Editor(base, path, state, mode) {
    override fun createToolbar(): EditorToolbar {
        return p5jsEditorToolbar(this)
    }

    override fun createFormatter(): Formatter {
        return p5jsFormatter()
    }

    override fun buildFileMenu(): JMenu {
        return super.buildFileMenu(arrayOf())
    }

    override fun buildSketchMenu(): JMenu {
        return super.buildSketchMenu(arrayOf())
    }

    override fun handleImportLibrary(name: String?) {
//        TODO("Not yet implemented")
    }

    override fun buildHelpMenu(): JMenu {
        return JMenu()
    }

    override fun handleOpenInternal(path: String?) {
        super.handleOpenInternal(path)
    }

    override fun getCommentPrefix(): String {
        return "//"
    }

    override fun internalCloseRunner() {
//        TODO("Not yet implemented")
    }

    override fun deactivateRun() {
//        TODO("Not yet implemented")
    }
}