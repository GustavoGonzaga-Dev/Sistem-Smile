package vendas;

import java.sql.*;
import java.util.Collections;
import java.util.List;

public class VendasController {
    public static final String URL = "jdbc:mariadb://localhost:3306/LOJA";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    Vendas v1 = new Vendas();
    Vendas v2 = new Vendas();
    Vendas v3 = new Vendas();

    public void carregar(){
        v1.setCodigoVenda(1);
        v1.setCodigoCliente(1);
        v1.setNomeCliente("Roberto");
        v1.setEndereco("Rua Catapimbas N°77, São Paulo SP");
        v1.setSituacao("Cancelado");
        v1.setData("11/04/2021");

        v2.setCodigoVenda(2);
        v2.setCodigoCliente(2);
        v2.setNomeCliente("Claudio");
        v2.setEndereco("Rua Carambolas N°88, São Paulo SP");
        v2.setSituacao("Entregue");
        v2.setData("08/05/2021");

        v3.setCodigoVenda(3);
        v3.setCodigoCliente(3);
        v3.setNomeCliente("Jurema");
        v3.setEndereco("Rua Cataporas N°99, São Paulo SP");
        v3.setSituacao("Preparando");
        v3.setData("16/11/2021");
    }

    public Vendas pesquisaCodigo(int codigo){
        if(v1.getCodigoVenda() == 0){
            carregar();
        }
        switch (codigo){
            case 1:
                return v1;
            case 2:
                return v2;
            case 3:
                return v3;
        }
        return null;
    }

    public void alterar(Vendas vd){
        if(vd.getCodigoVenda() == v1.getCodigoVenda()){
            v1.setSituacao(vd.getSituacao());
        }else if(vd.getCodigoVenda() == v2.getCodigoVenda()){
            v2.setSituacao(vd.getSituacao());
        }else if(vd.getCodigoVenda() == v3.getCodigoVenda()){
            v3.setSituacao(vd.getSituacao());
        }
    }
}
