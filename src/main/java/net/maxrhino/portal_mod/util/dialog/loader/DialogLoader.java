package net.maxrhino.portal_mod.util.dialog.loader;

import net.minecraft.client.resources.language.I18n;

import java.util.List;

public class DialogLoader {
    private List<TextPart> text = List.of();
    private final String translated;

    public DialogLoader(String key) {
        this.translated = I18n.get(key);
    }

    public void convert() {
        TextPart lastTextPart = new TextPart("");
        boolean isInTag = false;
        StringBuilder tagContent = new StringBuilder();

        for (char c : translated.toCharArray()) {
            if (c == '<') {
                isInTag = true;
                tagContent.setLength(0);
                text.add(lastTextPart);
                lastTextPart.reset();
            } else if (c == '>') {
                isInTag = false;

                // Parse the completed tag
                String tag = tagContent.toString();
                if (tag.startsWith("clr:")) {
                    String[] parts = tag.substring(4).split(",");
                    int r = Integer.parseInt(parts[0]);
                    int g = Integer.parseInt(parts[1]);
                    int b = Integer.parseInt(parts[2]);
                    lastTextPart.setColor(new TextPart.Color(r, g, b));
                } else if (tag.startsWith("sfx")) {
                    lastTextPart.toggleSfxText();
                } else {
                    switch (tag) {
                        case "I" -> lastTextPart.toggleItalic();
                        case "B" -> lastTextPart.toggleBold();
                    }
                }
            } else if (isInTag) {
                tagContent.append(c);
            } else {
                lastTextPart.addChar(c);
            }
        }
    }

    public List<TextPart> getText() {
        return text;
    }

    public static class TextPart {
        private String text;
        private boolean italic;
        private boolean bold;
        private Color color;
        private boolean isSfxText;

        public TextPart(String text) {
            this.text = text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void addChar(char c) {
            this.text += c;
        }

        public String text() {
            return text;
        }

        public void reset() {
            this.text = "";
            this.italic = false;
            this.bold = false;
            this.color = null;
            this.isSfxText = false;
        }

        // Text Tags
        // Getters

        public boolean isItalic() {
            return italic;
        }

        public boolean isBold() {
            return bold;
        }

        public Color getColor() {
            return color;
        }

        public boolean isSfxText() {
            return isSfxText;
        }

        // Setters

        public void setItalic(boolean italic) {
            this.italic = italic;
        }
        public void toggleItalic() {
            this.italic = !this.italic;
        }

        public void setBold(boolean bold) {
            this.bold = bold;
        }
        public void toggleBold() {
            this.bold = !this.bold;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public void setSfxText(boolean sfxText) {
            isSfxText = sfxText;
        }
        public void toggleSfxText() {
            this.isSfxText = !this.isSfxText;
        }

        public record Color(int r, int g, int b) { }
    }
}
