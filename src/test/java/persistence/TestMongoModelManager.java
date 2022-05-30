package persistence;

import framework.core.ModelManager;
import framework.core.ModelManagerFactory;
import framework.core.util.ConnectionUtils;
import model.CarroNovo;
import model.Pessoa;
import model.Planta;
import model.PlantaDocument;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

public class TestMongoModelManager {

    private ModelManager manager;

    @Before
    public void setUp(){
        this.manager = ModelManagerFactory.createModelManager(ModelManagerFactory.MONGO);
    }

    @Test
    public void testa_insert_na_base(){
        Pessoa p = new Pessoa();
        p.setNome("William Chiarato Vieria");
        p.setDataNascimento(Calendar.getInstance());

        manager.inserir(p);

        assertThat(p.getId()).isNotNull().isGreaterThan(0);
    }

    @Test
    public void testa_insert_na_base_id_em_string(){
        CarroNovo c = new CarroNovo();
        c.setNome("Lamborghini Murcielago");
        c.setDataCompra(Calendar.getInstance());

        manager.inserir(c);

        assertThat(c.getId()).isNotNull();
        System.out.println(c.getId());
    }
    
    @Test
    public void test_delete_id_string() {
        CarroNovo c = new CarroNovo();
        c.setId("622cded42be99b7ffabfdcad");

        manager.remover(c);
    }

    @Test
    public void test_delete_id_int() {
        Pessoa p = new Pessoa();
        p.setId(1);

        manager.remover(p);
    }
    
    @Test
    public void test_update_id_string() {
        CarroNovo c = new CarroNovo();
        c.setId("622e4cce9a5c4b4e08cf5912");
        c.setNome("Lamborghini Veneno");
        c.setDataCompra(Calendar.getInstance());

        manager.atualizar(c);
    }

    @Test
    public void test_update_id_int() {
        Pessoa p = new Pessoa();

        manager.atualizar(p);
    }

    @Test
    public void test_select_id_string() {
        CarroNovo c = new CarroNovo();
        c.setId("62376624865af908096b3b69");

        manager.consultar(c, c.getId());
    }

    @Test
    public void test_select_id_int() {
        Pessoa p = new Pessoa();
        p.setId(78670);

        manager.consultar(p, p.getId());
    }

    @Test
    public void test_insert_thousands() {
        long start = System.nanoTime();

        for(int i = 0; i < 1000000; i++) {
            PlantaDocument o = new PlantaDocument();
            o.setNome("Planta");
            o.setDataCadastro(new Date());
            o.setTipo(2);
            o.setExtinto(0);
            o.setClassificacao("Classificacao");
            o.setOrdem(1);
            o.setFumavel(1);
            o.setCor("Cor");

            //CarroNovo o = new CarroNovo();
            //o.setNome("Carro 1 " + (i + 1));
            //o.setDataCompra(Calendar.getInstance());

            manager.inserir(o);
        }

        long elapsedTime = System.nanoTime() - start;
        System.out.println(elapsedTime);
    }

    @Test
    public void test_update_thousands() {
        long start = System.nanoTime();

        for(int i = 0; i < 1000000; i++) {
            PlantaDocument o = new PlantaDocument();
            o.setId("623f9dbea13bbe72c667d98d");
            o.setNome("Pinheiro Alto");
            o.setDataCadastro(new Date());
            o.setTipo(2);
            o.setExtinto(0);
            o.setClassificacao("Arvore");
            o.setOrdem(1);
            o.setFumavel(1);
            o.setCor("Marrom");

            manager.atualizar(o);
        }

        long timeInNano = System.nanoTime() - start;
        System.out.println(timeInNano);
    }

    @Test
    public void test_select_thousands() {
        long start = System.nanoTime();

        for(int i = 0; i < 1000000; i++) {
            PlantaDocument o = new PlantaDocument();
            o.setId("623f9a3b2ca01414f84da03e");
            manager.consultar(o, o.getId());
        }

        long timeInNano = System.nanoTime() - start;
        System.out.println(timeInNano);
    }
}
