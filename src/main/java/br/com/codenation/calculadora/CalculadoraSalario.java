package br.com.codenation.calculadora;

public class CalculadoraSalario implements Descontos {
    private static final double SALARIO_MINIMO = 1039.00;

    public long calcularSalarioLiquido(double salarioBase) {
        validarSalarioInformado(salarioBase);
        double salarioDescontado = calcularDescontoInss(salarioBase);
        return Math.round(calcularDescontoIrrf(salarioDescontado));
    }

    private double calcularDescontoInss(double salarioBase) {
        if (!(salarioBase > SALARIO_MINIMO) || !(salarioBase <= 1500.00)) {
            return (salarioBase > 1500.00 && salarioBase <= 4000.00)
                    ? salarioBase * (1 - DESCONTO_INSS_FAIXA02)
                    : salarioBase * (1 - DESCONTO_INSS_FAIXA03);
        }
        return salarioBase - (salarioBase * DESCONTO_INSS_FAIXA01);
    }

    private double calcularDescontoIrrf(double salarioBase) {
        if (!verificarIsencaoIrrf(salarioBase)) {
            if (salarioBase <= 3000.00) {
                return salarioBase;
            }
            return (salarioBase > 3000.00 && salarioBase <= 6000.00)
                    ? salarioBase * (1 - DESCONTO_IRRF_FAIXA01)
                    : salarioBase * (1 - DESCONTO_IRRF_FAIXA02);
        } else {
            return 0.00;
        }
    }

    private void validarSalarioInformado(Double salarioBase) throws NullPointerException {
        if (salarioBase == null) throw new NullPointerException();
    }

    private boolean verificarIsencaoIrrf(double salariobase) {
        return salariobase <= SALARIO_MINIMO;
    }
}
