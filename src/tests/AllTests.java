package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestGame.class, TestPlayer.class, TestRunTournament.class,
		TestStrategy.class })
public class AllTests {

}
