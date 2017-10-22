package br.com.sodicas.api;

import java.io.File;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.datasources.DatasourcesFraction;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

public class Boot {

	public static void main(String[] args) throws Exception {
		try {
		Swarm swarm = new Swarm(args);
	     
	    swarm.fraction(
	        new DatasourcesFraction()
	        .dataSource("swarmDs", (ds) -> {
	          ds.jndiName("java:/sodicas");
	          ds.driverName("postgresql");
	          ds.connectionUrl("jdbc:postgresql://localhost:5432/sodicas?createDatabaseIfNotExist=true&&useSSL=false");
	          ds.userName("postgres");
	          ds.password("postgres");
	           
	        }));
	         
	        swarm.start();
	        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
	        
	        ClassLoader classLoader = Boot.class.getClassLoader();
	               
	        deployment.addAsWebInfResource(classLoader.getResource("beans.xml"),"beans.xml");
	        deployment.addAsWebInfResource(new File("src/main/resources/META-INF","persistence.xml"), "classes/META-INF/persistence.xml");
	         
	        deployment.addPackages(true, Package.getPackage("br.com.sodicas.api"));
	        deployment.addAllDependencies();
	         
	        swarm.deploy(deployment);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
