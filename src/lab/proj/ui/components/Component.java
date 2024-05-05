package lab.proj.ui.components;

import lab.proj.ui.drawables.Drawable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Component {
    protected  List<Drawable> drawables = new ArrayList<>();

    public void SetDrawables(List<Drawable> drawables) {
        this.drawables = drawables;
    }
    public abstract void Draw(JComponent target);
}
