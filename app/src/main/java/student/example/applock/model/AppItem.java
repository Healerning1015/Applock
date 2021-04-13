package student.example.applock.model;

import android.graphics.drawable.Drawable;

public class AppItem {
    private Drawable icon;
    private String name;
    private String packageName;

    public AppItem(Drawable icone, String name, String packageName) {
        this.icon = icone;
        this.name = name;
        this.packageName = packageName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
