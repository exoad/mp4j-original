package app.interfaces.event

import app.CLI
import app.core.PropertiesReader
import java.awt.RenderingHints
import java.awt.event.ComponentEvent
import java.awt.event.ComponentListener
import java.awt.geom.RoundRectangle2D
import javax.swing.JComponent
import javax.swing.JFrame

/**
 * @author Jack Meng
 */
class FrameOrganizer(private val frame: JFrame) : JComponent(), ComponentListener {
    private val pr: PropertiesReader
    override fun componentResized(e: ComponentEvent) {
        frame.shape = RoundRectangle2D.Float(0F, 0F, frame.width.toFloat(), frame.height.toFloat(), 5F, 5F)
        frame.repaint()
        CLI.print("Window transparency: " + pr.getVal("gui.window_transparency"))
        frame.opacity = pr.getVal("gui.window_transparency").toFloat()
    }

    override fun componentMoved(e: ComponentEvent) {
        // UNUSED
    }

    override fun componentShown(e: ComponentEvent) {
        // UNUSED
    }

    override fun componentHidden(e: ComponentEvent) {
        // UNUSED
    }

    init {
        frame.isUndecorated = true
        val qualityHints = RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON)
        qualityHints[RenderingHints.KEY_RENDERING] = RenderingHints.VALUE_RENDER_QUALITY
        pr = PropertiesReader()
    }
}