package ar.edu.utn.frba.dds.dao;

import ar.edu.utn.frba.dds.Usuario;
import org.junit.Test;

public class UsuarioDaoTest {

    @Test
    public void getUser() {

        UsuarioDao dao = new UsuarioDao();
        Usuario u =  dao.getUser("JohnDoe88@hotmail.com");

        if (u != null && u.getPassword().equals("1234")){

            if(dao.isClient(u)!= null)
            {
                System.out.println("Es admin");
            }
            else
            {
                System.out.println("Es cliente");
            }
        }
    }

    @Test
    public void isClient() {
    }
}