package com.anangkur.synrgychapter7.presentation.auth.login

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.anangkur.synrgychapter7.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    @get:Rule
    var activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun testViewIsVisible() {
        // make sure title is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.tv_title),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed(),
            ),
        )

        // make sure edit text username is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.et_username),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed(),
            ),
        )

        // make sure edit text password is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.et_password),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed(),
            ),
        )

        // make sure button login is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.button_login),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed(),
            ),
        )

        // make sure button change language is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.button_change_language),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed(),
            ),
        )

        // make sure button register is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.button_register),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed(),
            ),
        )
    }

    @Test
    fun testEnableButtonLogin() {
        // make sure button login is disabled
        Espresso.onView(
            ViewMatchers.withId(R.id.button_login),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isNotEnabled(),
            ),
        )

        // make sure button login keep disabled after edit text username is filled
        // but edit text password is empty
        Espresso.onView(
            ViewMatchers.withId(R.id.et_username),
        ).perform(
            ViewActions.typeText("anangkur"),
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.button_login),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isNotEnabled(),
            ),
        )

        // make sure button login is enabled after edit text password is filled
        // and edit text username is also filled
        Espresso.onView(
            ViewMatchers.withId(R.id.et_password),
        ).perform(
            ViewActions.typeText("123456"),
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.button_login),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isEnabled(),
            ),
        )

        // make sure button login is disabled after edit text password is cleared
        Espresso.onView(
            ViewMatchers.withId(R.id.et_password),
        ).perform(
            ViewActions.clearText(),
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.button_login),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isNotEnabled(),
            ),
        )

        // make sure button login is enabled again after edit text password is filled again
        Espresso.onView(
            ViewMatchers.withId(R.id.et_password),
        ).perform(
            ViewActions.typeText("123456"),
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.button_login),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isEnabled(),
            ),
        )

        // make sure button login is disabled again after edit text username is cleared
        Espresso.onView(
            ViewMatchers.withId(R.id.et_username),
        ).perform(
            ViewActions.clearText(),
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.button_login),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isNotEnabled(),
            ),
        )
    }
}
