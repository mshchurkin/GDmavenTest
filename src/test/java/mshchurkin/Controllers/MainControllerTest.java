package mshchurkin.Controllers;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;


@RunWith(Arquillian.class)
public class MainControllerTest {
    @org.junit.Test
    public void data() throws Exception {

    }

    @org.junit.Test
    public void columnsInit() throws Exception {
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(MainController.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
