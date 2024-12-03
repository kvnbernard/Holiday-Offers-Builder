package tests.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	PathFinderTest.class,
	TripRepositoryTest.class,
	PathFinderTest.class
})
public class FullTestSuite {

}
