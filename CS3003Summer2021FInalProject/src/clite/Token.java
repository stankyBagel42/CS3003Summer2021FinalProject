// Sourced from https://github.com/NickStephens/Clite
package clite;


public class Token {

    private static final int KEYWORDS = TokenType.Eof.ordinal();

    private static final String[] reserved = new String[KEYWORDS];
    private static Token[] token = new Token[KEYWORDS];

    public static final Token eofTok = new Token(TokenType.Eof, "<<EOF>>");
    public static final Token boolTok = new Token(TokenType.Bool, "bool");
    public static final Token charTok = new Token(TokenType.Char, "char");
    public static final Token elseTok = new Token(TokenType.Else, "else");
    public static final Token falseTok = new Token(TokenType.False, "false");
    public static final Token floatTok = new Token(TokenType.Float, "float");
    public static final Token ifTok = new Token(TokenType.If, "if");
    public static final Token intTok = new Token(TokenType.Int, "int");
    public static final Token longTok = new Token(TokenType.Long, "long");
    public static final Token mainTok = new Token(TokenType.Main, "main");
    public static final Token trueTok = new Token(TokenType.True, "true");
    public static final Token whileTok = new Token(TokenType.While, "while");
    public static final Token voidTok = new Token(TokenType.Void, "void");
    public static final Token returnTok = new Token(TokenType.Return, "return");
    public static final Token printTok = new Token(TokenType.Print, "print");
    public static final Token leftBraceTok = new Token(TokenType.LeftBrace, "{");
    public static final Token rightBraceTok = new Token(TokenType.RightBrace, "}");
    public static final Token leftBracketTok = new Token(TokenType.LeftBracket, "[");
    public static final Token rightBracketTok = new Token(TokenType.RightBracket, "]");
    public static final Token leftParenTok = new Token(TokenType.LeftParen, "(");
    public static final Token rightParenTok = new Token(TokenType.RightParen, ")");
    public static final Token semicolonTok = new Token(TokenType.Semicolon, ";");
    public static final Token commaTok = new Token(TokenType.Comma, ",");
    public static final Token assignTok = new Token(TokenType.Assign, "=");
    public static final Token eqeqTok = new Token(TokenType.Equals, "==");
    public static final Token ltTok = new Token(TokenType.Less, "<");
    public static final Token lteqTok = new Token(TokenType.LessEqual, "<=");
    public static final Token gtTok = new Token(TokenType.Greater, ">");
    public static final Token gteqTok = new Token(TokenType.GreaterEqual, ">=");
    public static final Token notTok = new Token(TokenType.Not, "!");
    public static final Token noteqTok = new Token(TokenType.NotEqual, "!=");
    public static final Token plusTok = new Token(TokenType.Plus, "+");
    public static final Token minusTok = new Token(TokenType.Minus, "-");
    public static final Token multiplyTok = new Token(TokenType.Multiply, "*");
    public static final Token divideTok = new Token(TokenType.Divide, "/");
    public static final Token exponentTok = new Token(TokenType.Exponent, "^");
    public static final Token andTok = new Token(TokenType.And, "&&");
    public static final Token orTok = new Token(TokenType.Or, "||");
    // Our added tokens
    // Exponent - Binary Operator
    // For Loop - Control Structure 
    public static final Token forTok = new Token(TokenType.For, "for");
    // Long - Data type


    private TokenType type;
    private String value = "";

    // Constructor for tokens
    private Token(TokenType t, String v) {
        type = t;
        value = v;
        // Compare the number of characters in the token to EOF, if it has less characters than the EOF token, it's
        // cool to be a reserved word/type (the longest is 6 in "return")
        if (t.compareTo(TokenType.Eof) < 0) // If t is a reserved word and type 
        {
            // get the place where this tokenType was defined in the enum (in TokenType.java)
            int ti = t.ordinal();
            // Put the value in the reserved strings list
            reserved[ti] = v;
            // Place a reference to this token object in the token list
            token[ti] = this;
        }
    }

    // kind of like GET TYPE but its just type instead >:(
    public TokenType type() {
        return type;
    }

    // kind of like GET VALUE but its just value instead >:(
    public String value() {
        return value;
    }

    // Convert from string to keyword token (IF ITS A KEYWORD), otherwise return a new identifier since its an identifier
    public static Token keyword(String name) {
        char ch = name.charAt(0);
        // keywords dont start w/ caps, so return a new identifier token with the name
        if (ch >= 'A' && ch <= 'Z') return mkIdentTok(name);
        // Go thru all of the keywords to see if name is the same as any of them
        for (int i = 0; i < KEYWORDS; i++)
            if (name.equals(reserved[i])) return token[i];
        // If we haven't returned yet, its not a keyword and it is an identifier
        return mkIdentTok(name);
    } // keyword

    // Make identifier
    public static Token mkIdentTok(String name) {
        return new Token(TokenType.Identifier, name);
    }

    public static Token mkIntLiteral(String name) {
        return new Token(TokenType.IntLiteral, name);
    }

    public static Token mkLongLiteral(String name) {
        return new Token(TokenType.LongLiteral, name);
    }

    public static Token mkFloatLiteral(String name) {
        return new Token(TokenType.FloatLiteral, name);
    }

    public static Token mkCharLiteral(String name) {
        return new Token(TokenType.CharLiteral, name);
    }

    public String toString() {
        // If this token's type is less in length than the identifier token, just return the token's value
        // (because its not an identifier). Else, return the type and the value since its an identifier
        if (type.compareTo(TokenType.Identifier) < 0) return value;
        return type + "\t" + value;
    } // toString

    public static void main(String[] args) {
        System.out.println(eofTok);
        System.out.println(whileTok);
    }
} // Token
