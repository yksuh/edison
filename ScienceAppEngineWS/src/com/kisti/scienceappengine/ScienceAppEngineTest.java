package com.kisti.scienceappengine;

import static org.junit.Assert.*;

import org.junit.Test;

public class ScienceAppEngineTest {

	//@Test
	public void testRetrieveModuleByKeyword() {
		ScienceAppEngine sae = new ScienceAppEngine(true);
		sae.retrieveModuleByKeyword("gamess/gamess");
	}

	//@Test
	public void testListModules() {
	//	fail("Not yet implemented");
		ScienceAppEngine sae = new ScienceAppEngine();
		sae.listModules();
	}

	//@Test
	public void testRetrieveLibraryByKeyword() {
		ScienceAppEngine sae = new ScienceAppEngine(true);
		sae.retrieveLibraryByKeyword("libORBitCosNaming");
	}
	
	//@Test
	public void testRetrieveLibrary() {
		ScienceAppEngine sae = new ScienceAppEngine();
		sae.retrieveLibrary();
	}

	//@Test
	public void testLoadModuleByKeyword() {
		ScienceAppEngine sae = new ScienceAppEngine(true);
		sae.loadModuleByKeyword("gamess/gamess");
	}
	
	//@Test
	public void testShowAvailableModules() {
		ScienceAppEngine sae = new ScienceAppEngine(true);
		sae.showAvailableModules();
	}
	
	//@Test
	public void testInstallLibrary() {
		ScienceAppEngine sae = new ScienceAppEngine(true);
		sae.installLibrary(null, null);
	}
	
	//@Test
	public void testScienceApp() {
		ScienceAppEngine sae = new ScienceAppEngine(true);
		sae.doScienceAppTesting("calculator", null, null, null, null);
	}
	
	//@Test
	public void testSubmitJob() {
		ScienceAppEngine sae = new ScienceAppEngine(true);
		sae.submitJob(Constants.TEST_SCIAPP_DIR+"a.out", Constants.TEST_SCIAPP_DIR +"input.txt", Constants.TEST_SCIAPP_OUTPUT_DIR+"output.txt");
	}
	
	//@Test
	public void testConnectDB() {
		ScienceAppEngine sae = new ScienceAppEngine(true);
		sae.connectToDB();
	}
	
	//@Test
	public void testDisconnectDB() {
		ScienceAppEngine sae = new ScienceAppEngine(true);
		sae.disconnectToDB();
	}
	
	//@Test
	public void testCreateTables(){
		ScienceAppEngine sae = new ScienceAppEngine();
		sae.createTestTables();
		sae.disconnectToDB();
	}
	
	//@Test
	public void testDropTable() {
		ScienceAppEngine sae = new ScienceAppEngine(true);
		sae.dropTestTables();
		sae.disconnectToDB();
	}
	
	@Test
	public void testRetrieveTestStatus() {
		ScienceAppEngine sae = new ScienceAppEngine(true);
		sae.retrieveStatusForSolverInTest();
		sae.disconnectToDB();
	}
}
