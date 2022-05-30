package persistence;

import framework.core.ModelManager;
import framework.core.ModelManagerFactory;
import framework.core.util.ConnectionUtils;
import model.CarroNovo;
import model.Pessoa;
import model.Planta;
import org.junit.Before;
import org.junit.Test;

import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class TestPostgresModelManager {

    private ModelManager manager;
    private Connection connection = null;

    @Before
    public void setUp(){
        this.manager = ModelManagerFactory.createModelManager(ModelManagerFactory.POSTGRES);
    }

    @Test
    public void testa_insert_na_base(){
        Pessoa p = new Pessoa();
        p.setNome("William V.");
        p.setDataNascimento(Calendar.getInstance());

        manager.inserir(p);
        assertThat(p.getId()).isNotNull().isGreaterThan(0);
    }

    @Test
    public void testa_insert_na_base_id_em_string(){
        CarroNovo c = new CarroNovo();
        c.setNome("Lamborguini");
        c.setDataCompra(Calendar.getInstance());

        manager.inserir(c);

        assertThat(c.getId()).isNotNull();
        System.out.println(c.getId());
    }
    
    @Test
    public void test_delete() {
        Pessoa p = new Pessoa();
        p.setId(1);
        p.setNome("William");
        p.setDataNascimento(Calendar.getInstance());

        manager.remover(p);
    }
    
    @Test
    public void test_update() {
        Pessoa p = new Pessoa();
        p.setId(1);
        p.setNome("William editado");
        p.setDataNascimento(Calendar.getInstance());

        manager.atualizar(p);
    }
    
    @Test
    public void test_select() {
        Pessoa p = new Pessoa();
        p.setId(678675);

        manager.consultar(p, p.getId());
    }

    @Test
    public void test_insert_thousands() {
        long start = System.nanoTime();

        for(int j = 0; j < 10000; j++) {
            Planta o = new Planta();
            o.setNome("Planta");
            o.setDataCadastro(new Date());
            o.setTipo(2);
            o.setExtinto(0);
            o.setClassificacao("Classificacao");
            o.setOrdem(1);
            o.setFumavel(1);
            o.setCor("Cor");

            /*Pessoa o = new Pessoa();
            o.setNome("Usuario " + (j + 1));
            o.setDataNascimento(Calendar.getInstance());*/

            /*CarroNovo o = new CarroNovo();
            o.setNome("Carro 2 " + (i + 1));
            o.setDataCompra(Calendar.getInstance());
            manager.inserir(o);*/

            manager.inserir(o);
            //assertThat(o.getId()).isNotNull().isGreaterThan(0);
        }

        ConnectionUtils.getInstance().closeSilenlty(connection);

        long timeInNano = System.nanoTime() - start;
        System.out.println(timeInNano);
    }

    @Test
    public void test_update_thousands() {
        long start = System.nanoTime();

        for(int j = 1; j < 10001; j++) {
            Planta o = new Planta();
            o.setId(j);
            o.setNome("Pinheiro");
            o.setDataCadastro(new Date());
            o.setTipo(2);
            o.setExtinto(0);
            o.setClassificacao("Arvore");
            o.setOrdem(1);
            o.setFumavel(1);
            o.setCor("Marrom");

            manager.atualizar(o);
        }

        ConnectionUtils.getInstance().closeSilenlty(connection);

        long timeInNano = System.nanoTime() - start;
        System.out.println(timeInNano);
    }

    @Test
    public void test_select_thousands() {
        long start = System.nanoTime();

        for(int j = 1; j < 1000001; j++) {
            Planta o = new Planta();
            o.setId(j);

            manager.consultar(o, o.getId());
        }

        ConnectionUtils.getInstance().closeSilenlty(connection);

        long timeInNano = System.nanoTime() - start;
        System.out.println(timeInNano);
    }
}
