package teste;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({TestaFornecedor.class, TestaGerarId.class, TestaLogin.class, TestaUsuario.class,
	TestaProduto.class,TestaCardapio.class, TestaVendas.class, TestaCliente.class})
public class AllTests {

}
