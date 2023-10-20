import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Optional;

public class Interpreter implements Expr.Visitor<Object> {

    @Override
    public Object visitBinaryExpr(Expr.Binary expr) {
        Object left = evaluate(expr.left);
        Object right = evaluate(expr.right);
        switch (expr.operator.type) {
            case BANG_EQUAL: return !isEqual(left, right);
            case EQUAL_EQUAL: return isEqual(left, right);
            case GREATER:
                checkNumberAndOperand(expr.operator, left, right);
                return (double)left > (double)right;
            case GREATER_EQUAL:
                checkNumberAndOperand(expr.operator, left, right);
                return (double)left >= (double)right;
            case LESS:
                checkNumberAndOperand(expr.operator, left, right);
                return (double)left < (double)right;
            case LESS_EQUAL:
                checkNumberAndOperand(expr.operator, left, right);
                return (double)left <= (double)right;
            case MINUS:
                checkNumberAndOperand(expr.operator, left, right);
                return (double) left - (double) right;
            case PLUS:
                if (left instanceof Double && right instanceof Double) {
                    return (double) left + (double) right;
                }
                if (left instanceof String && right instanceof String) {
                    return (String) left + (String) right;
                }
            case SLASH:
                checkNumberAndOperand(expr.operator, left, right);
                return (double) left / (double) right;
            case STAR:
                checkNumberAndOperand(expr.operator, left, right);
                return (double) left * (double) right;
        }
        return null;
    }

    private void checkNumberAndOperand(Token operator, Object left, Object right) {
        if (left instanceof Double && right instanceof Double) return;
        throw new RunTimeError(operator, "Operand must be number");
    }

    private boolean isEqual(Object a, Object b) {
        if(a==null&&b==null) return true;
        if(a==null) return false;
        return a.equals(b);
    }

    void interpret(Expr expression) {
        try {
            Object value = evaluate(expression);
            System.out.println(stringify(value));
        } catch (RunTimeError error) {
            Lox.runtimeError(error);
        }
    }

    private String stringify(Object value) {
        if(value==null) return "nil";
        if (value instanceof Double) {
            String text = value.toString();
            if (text.endsWith(".0")) {
                text = text.substring(0, text.length());
            }
            return text;
        }
        return value.toString();
    }


    @Override
    public Object visitGroupingExpr(Expr.Grouping expr) {
        return evaluate(expr.expression);
    }

    private Object evaluate(Expr expression) {
        return expression.accept(this);
    }

    @Override
    public Object visitLiteralExpr(Expr.Literal expr) {
        return expr.value;
    }

    @Override
    public Object visitUnaryExpr(Expr.Unary expr) {
        Object right = evaluate(expr.right);
        switch (expr.operator.type) {
            case BANG:
                return !isTruthy(right);
            case MINUS:
                checkNumberAndOperand(expr.operator, right);
                return -(double) right;
        }
        return null;
    }

    private void checkNumberAndOperand(Token operator, Object right) {
        if(right instanceof Double) return;
        throw new RunTimeError(operator, "Operand must be a number");
    }

    private boolean isTruthy(Object right) {
        if(right==null) return false;
        if(right instanceof Boolean) return (boolean) right;
        return true;
    }
}
