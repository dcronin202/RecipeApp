package com.example.android.recipeapp;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import androidx.core.widget.NestedScrollView;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;


@RunWith(AndroidJUnit4.class)
public class SummaryActivityBasicTest {

    private static final String BUTTON_TEXT = "Step-by-Step Mode";

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void summaryButtonTextDisplayTest() throws Exception {

        // Delay test so network call can be made
        Thread.sleep(1000);

        // Find and perform action on the view
        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.recipe_list_layout),
                childAtPosition(allOf(withId(R.id.recyclerview_main),
                childAtPosition(withClassName(is("android.widget.LinearLayout")),
                0)), 0), isDisplayed()));

        linearLayout.perform(click());

        // Check if the button text is displayed correctly
        onView(withId(R.id.button_recipe_steps)).check(matches(withText(BUTTON_TEXT)));

    }

    @Test
    public void summaryIngredientsDisplayTest() throws Exception {

        Thread.sleep(1000);

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.recipe_list_layout),
                childAtPosition(allOf(withId(R.id.recyclerview_main),
                childAtPosition(withClassName(is("android.widget.LinearLayout")),
                0)), 0), isDisplayed()));

        linearLayout.perform(click());

        ViewInteraction summaryIngredients = onView(
                allOf(withId(R.id.recyclerview_ingredients),
                childAtPosition(childAtPosition(withClassName(is("androidx.core.widget.NestedScrollView")),
                0), 3)));

        // Check to confirm that Ingredients are displayed
        summaryIngredients.check(matches(isDisplayed()));

    }

    @Test
    public void summaryButtonDisplayTest() throws Exception {

        Thread.sleep(1000);

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.recipe_list_layout),
                childAtPosition(allOf(withId(R.id.recyclerview_main),
                childAtPosition(withClassName(is("android.widget.LinearLayout")),
                0)), 0), isDisplayed()));

        linearLayout.perform(click());

        // Check to confirm that the step-by-step button is completely displayed
        onView(withId(R.id.button_recipe_steps))
                .perform(nestedScrollTo())
                .check(matches(isCompletelyDisplayed()));

    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }


    public static ViewAction nestedScrollTo() {
        return new ViewAction() {

            @Override
            public Matcher<View> getConstraints() {
                return Matchers.allOf(
                        isDescendantOfA(isAssignableFrom(NestedScrollView.class)),
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE));
            }

            @Override
            public String getDescription() {
                return "View is not NestedScrollView";
            }

            @Override
            public void perform(UiController uiController, View view) {
                try {
                    NestedScrollView nestedScrollView = (NestedScrollView)
                            findFirstParentLayoutOfClass(view, NestedScrollView.class);
                    if (nestedScrollView != null) {
                        nestedScrollView.scrollTo(0, view.getTop());
                    } else {
                        throw new Exception("Unable to find NestedScrollView parent.");
                    }
                } catch (Exception e) {
                    throw new PerformException.Builder()
                            .withActionDescription(this.getDescription())
                            .withViewDescription(HumanReadables.describe(view))
                            .withCause(e)
                            .build();
                }
                uiController.loopMainThreadUntilIdle();
            }

        };
    }

    private static View findFirstParentLayoutOfClass(View view, Class<? extends View> parentClass) {
        ViewParent parent = new FrameLayout(view.getContext());
        ViewParent incrementView = null;
        int i = 0;
        while (parent != null && !(parent.getClass() == parentClass)) {
            if (i == 0) {
                parent = findParent(view);
            } else {
                parent = findParent(incrementView);
            }
            incrementView = parent;
            i++;
        }
        return (View) parent;
    }

    private static ViewParent findParent(View view) {
        return view.getParent();
    }

    private static ViewParent findParent(ViewParent view) {
        return view.getParent();
    }

}
