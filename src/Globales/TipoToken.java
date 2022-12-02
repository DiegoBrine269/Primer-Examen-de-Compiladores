package Globales;

public interface TipoToken {

    // Plabras reservadas
    final String AUTO = "auto";
    final String BREAK = "break";
    final String CASE = "case";
    final String CHAR = "char";
    final String CONST = "const";
    final String CONTINUE = "continue";
    final String DEFAULT = "default";
    final String DO = "do";
    final String DOUBLE = "double";
    final String ELSE = "else";
    final String ENUM = "enum";
    final String EXTERN = "extern";
    final String FLOAT = "float";
    final String FOR = "for";
    final String GOTO = "goto";
    final String IF = "if";
    final String INCLUDE = "include";
    final String INT = "int";
    final String LONG = "long";
    final String MAIN = "main";
    final String REGISTER = "register";
    final String RETURN = "return";
    final String SHORT = "short";
    final String SIGNED = "signed";
    final String SIZEOF = "sizeof";
    final String STATIC = "static";
    final String STRUCT = "struct";
    final String SWITCH = "switch";
    final String TYPEDEF = "typedef";
    final String UNION = "union";
    final String UNSIGNED = "unsigned";
    final String VOID = "void";
    final String VOLATILE = "volatile";
    final String WHILE = "while";
    final String _PACKED = "_Packed";

    //Identificador
    final String ID = "ID";

    final String NUM = "NUM";
    final String LIT = "LIT";

    //Operadores aritméticos y relacionales
    final String OP_A = "OP_A";
    final String OP_R = "OP_R";

    //Asignación (=)
    final String ASIGN = "ASIGN";

    //Fin de instricción (;)
    final String EOI = "EOI";

    // Símbolos especiales
    final String SE = "SE";

    // Puntuación
    final String PUNT = "PUNT";

    //Directivas #ifdef, #else, #endif
    final String DIRECT = "DIREC";

    //Bibliotecas como stdio.h
    final String BIBLIO = "BIBLIO";

    //Coma
    final String COMA = "COMA";
}
