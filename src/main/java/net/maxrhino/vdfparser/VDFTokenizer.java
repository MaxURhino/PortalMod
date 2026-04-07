package net.maxrhino.vdfparser;

public class VDFTokenizer {
    private final String input;
    private int pos = 0;

    public VDFTokenizer(String input) {
        this.input = input;
    }

    public String nextToken() {
        skipWhitespace();
        if (pos >= input.length()) return null;
        char c = input.charAt(pos);
        if (c == '{' || c == '}') {
            pos++;
            return String.valueOf(c);
        }
        if (c == '"') {
            return readString();
        }
        return readUnquoted();
    }

    private String readString() {
        pos++;
        StringBuilder sb = new StringBuilder();
        while (pos < input.length()) {
            char c = input.charAt(pos++);
            if (c == '\\') {
                if (pos >= input.length()) break;
                char next = input.charAt(pos++);
                switch (next) {
                    case 'n': sb.append('\n'); break;
                    case 't': sb.append('\t'); break;
                    case '"': sb.append('"'); break;
                    case '\\': sb.append('\\'); break;
                    default: sb.append(next); break;
                }
                continue;
            }
            if (c == '"') break;
            sb.append(c);
        }
        return sb.toString();
    }

    private String readUnquoted() {
        StringBuilder sb = new StringBuilder();
        while (pos < input.length()) {
            char c = input.charAt(pos);
            if (Character.isWhitespace(c) || c == '{' || c == '}')
                break;
            sb.append(c);
            pos++;
        }
        return sb.toString();
    }

    private void skipWhitespace() {
        while (pos < input.length()) {
            char c = input.charAt(pos);
            if (Character.isWhitespace(c)) {
                pos++;
                continue;
            }
            if (c == '/' && pos + 1 < input.length() && input.charAt(pos + 1) == '/') {
                pos += 2;
                while (pos < input.length() && input.charAt(pos) != '\n') {
                    pos++;
                }
                continue;
            }
            break;
        }
    }
}