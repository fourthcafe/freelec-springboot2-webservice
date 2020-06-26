package dev.fourthcafe.springboot.web;

import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;


public class ProfileControllerUnitTest {

	@Test
	public void real_profile이_조회된다() {
		// given
		String expectedProfile = "real";
		final MockEnvironment env = new MockEnvironment();
		env.addActiveProfile(expectedProfile);
		env.addActiveProfile("oauth");
		env.addActiveProfile("real-db");

		final ProfileController controller = new ProfileController(env);

		//when
		String profile = controller.profile();

		//then
		assertThat(profile).isEqualTo(expectedProfile);
	}


	@Test
	public void real_profile이_없으면_첫번째가_조회된다() {
		//given
		String expectedProfile = "oauth";
		MockEnvironment env = new MockEnvironment();

		env.addActiveProfile(expectedProfile);
		env.addActiveProfile("real-db");

		final ProfileController controller = new ProfileController(env);

		//when
		final String profile = controller.profile();

		//then
		assertThat(profile).isEqualTo(expectedProfile);
	}


	@Test
	public void active_profile이_없으면_default가_조회된다() {
		//given
		final String expectedProfile = "default";
		final MockEnvironment env = new MockEnvironment();
		final ProfileController controller = new ProfileController(env);

		//when
		final String profile = controller.profile();

		//then
		assertThat(profile).isEqualTo(expectedProfile);
	}
}