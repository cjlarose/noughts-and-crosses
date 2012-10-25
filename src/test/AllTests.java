package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BeginnerStrategyTest.class, GameTest.class,
		IntermediateStrategyTest.class, PlayerTest.class,
		RunTournamentTest.class })
public class AllTests {

}
