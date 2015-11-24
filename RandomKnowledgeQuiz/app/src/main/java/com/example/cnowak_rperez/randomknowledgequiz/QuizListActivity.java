package com.example.cnowak_rperez.randomknowledgequiz;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class QuizListActivity extends ListActivity {

    private boolean instantFeedbackEnabled;
    private int timeLimit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new QuizAdapter());
        SharedPreferences quizSettings = getSharedPreferences("QuizSettings", 0);
        this.timeLimit = quizSettings.getInt("timeLimit", 20);
        this.instantFeedbackEnabled = quizSettings.getBoolean("instantFeedbackEnabled", false);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        Intent intent = new Intent(QuizListActivity.this, QuizActivity.class);
        intent.putExtra("Name",QUIZZES[position].getName());
        intent.putExtra("Description", QUIZZES[position].getDescription());
        intent.putExtra("Quiz", QUIZZES[position]);
        intent.putExtra("instantFeedbackEnabled", instantFeedbackEnabled);
        intent.putExtra("timeLimit", timeLimit);
        //intent.putExtra("Description", QUIZZES[position].getLongDescription());
        //intent.putExtra("Year", HOME_CONSOLES[position].getYear());
        //intent.putExtra("Icon", HomeConsole.getIconResource(HOME_CONSOLES[position].getCompany()));
        //intent.putExtra("Image", HomeConsole.getPhotoResource(HOME_CONSOLES[position].getName()));
        //intent.putExtra("Generation", HOME_CONSOLES[position].getGeneration());
        //intent.putExtra("Demo", HomeConsole.getLink(HOME_CONSOLES[position].getName()));
        startActivity(intent);
    }

    class QuizAdapter extends BaseAdapter {
        private LayoutInflater inflater;

        @Override
        public int getCount(){return QUIZZES.length;}

        @Override
        public Object getItem(int i){return QUIZZES[i];}

        @Override
        public long getItemId(int i){return i;}

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View row = convertView;
            if(convertView == null){
                if(inflater == null) inflater = (LayoutInflater)
                        QuizListActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.activity_quiz_list, parent, false);
            }


            ImageView icon = (ImageView) row.findViewById(R.id.listImage);
            TextView name = (TextView) row.findViewById(R.id.listName);
            TextView description = (TextView) row.findViewById(R.id.listDesc);

            Quiz quiz = QUIZZES[position];
            name.setText(quiz.getName());
            description.setText(quiz.getDescription());
            icon.setImageResource(Quiz.getIconResource(quiz.getCategory()));
            //icon.setImageResource(HomeConsole.getIconResource(console.getCompany()));

            return row;
        }
    }

    //private static String[] possible = new String[4];
    private static final Question[] QUESTIONS_GEOGRAPHY ={
            new Question("What continent has the fewest flowing plants?", "Antarctica", "Australia","Asia","Antarctica", "Europe",1, Question.Category.Geography),
            new Question("The South Shetland Islands, which are claimed by the United Kingdom, Chile, and Argentina, are off the coast of which continent?", "Antarctica", "North America","Antarctica","Asia", "Europe", 2, Question.Category.Geography),
            new Question("Many klongs, or canals, in the most populous city on the Indochina Peninsula have been filled in and replaced by streets. Name this city?", "Bangkok", "Bangkok", "Singapore", "Kolkata", "Hanoi", 3, Question.Category.Geography),
            new Question("During World War II, the United States temporarily seized control of the island of Okinawa from which country?", "Japan", "Germany", "Japan", "Russia", "Italy", 4, Question.Category.Geography),
            new Question("The Great Circle at zero degrees latitude is primarily known by what name?", "Equator", "Tropic of Cancer", "Equator", "Prime Meridian", "Tropic of Capricorn", 5, Question.Category.Geography),
            //new Question("For 250 years Spanish galleons carried on a flourishing trade between Acapulco and a port on an island in East Asia. Name this port, which served as Spain's trade center for East Asia?", "Manila", "Manila", "Palau", "Taipei", "Macau", 6, Question.Category.Geography),
            new Question("Which river is farther north?", "Potomac River", "Rio Grande", "Nile River", "Chattahoochee", "Potomac River", 6, Question.Category.Geography),
            new Question("Silesia, one of Europe's principal coal-producing regions, is mostly in what country?", "Poland", "Poland", "Slovakia", "Austria", "Turkey", 7, Question.Category.Geography),
            new Question("Iceland, Corsica, and Malta are islands that are part of which continent?", "Europe", "Europe", "Australia", "Antarctica", "Africa", 8, Question.Category.Geography),
            new Question("A mixture of mud and straw is a popular building material in the southwestern United States. What is this building material called in this region?", "Adobe", "Clay", "Brick", "Adobe", "Cement", 9, Question.Category.Geography),
            new Question("Gran Paradiso National Park was established to provide protected habitat for ibex. This park is south of the Matterhorn in what country?", "Italy", "Switzerland", "Italy", "Austria", "France", 10, Question.Category.Geography),


    };
    private static final Question[] QUESTIONS_ANIMALS ={
            new Question("Reticulated means what?", "Diamond Pattern", "Long", "Diamond Pattern", "Tail", "Spotty Pattern", 1, Question.Category.Animals),
            new Question("Which two fish are known as living fossils?", "Arrapaima and Coelacanth", "Mandarinfish and Japanese Angler", "Bitterling and Pond Smelt", "Barracuda and Bicuda", "Arrapaima and Coelacanth", 2, Question.Category.Animals),
            new Question("Where do Pallas Cats come from?", "Nepal and India", "Britain and Australia", "China and Japan", "Nepal and India", "America and Canada", 3, Question.Category.Animals),
            //new Question("Dholes are what?", "Dogs", "Antelopes", "Dogs", "Wolves", "Cats", 3, Question.Category.Animals),
            new Question("The largest member of the dolphin family is a what?", "Orca", "Orca", "False Killer Whale", "Risso's dolphin", "Commerson's dolphin", 4, Question.Category.Animals),
            new Question("Which one of these species is endangered?", "All of the species of Rhino", "Black Rhino", "White Rhino", "Short-Horned Rhino", "All of the species of Rhino", 5, Question.Category.Animals),
            new Question("Which of these animals are venomous?", "Komodo Dragon", "Grass Snake", "Horse Leech", "Komodo Dragon", "Cottonmouth Snake", 6, Question.Category.Animals),
            new Question("What is a group of penguins called?", "Rockery", "Flock", "Rockery", "Leap", "Pride", 7, Question.Category.Animals),
            new Question("Out of the following which animal is most poisonous?", "Sea Wasp", "Sea Wasp", "Black Widow Spider", "Coral Snake", "King Cobra", 8, Question.Category.Animals),
            new Question("Why does a tiger scratch a tree?", "to mark its land", "to show animals how strong it is", "to sharpen its claws", "to mark its land","Under-appreciated artist", 9, Question.Category.Animals),
            new Question("Which of these is the fastest?", "Peregrine Falcon", "Barracuda", "Peregrine Falcon", "Cheetah", "My grandfather", 10, Question.Category.Animals),


    };
    private static final Question[] QUESTIONS_MISC ={
            new Question("Which popular spirit can be made from potatoes?", "Vodkas", "Whiskey", "Vodkas", "Beer", "Wine", 1, Question.Category.Misc),
            new Question("If it is 8am in California, what time is it in Missouri?", "10am", "9am", "10am", "12am", "8am", 2, Question.Category.Misc),
            new Question("Hellenikon international airport is in which country?", "Greece", "Switzerland", "Germany", "France", "Greece", 3, Question.Category.Misc),
            new Question("What does D stand for in the Computer Assisted CAD?", "Design", "Development", "Design", "Decoding", "Decimal", 3, Question.Category.Misc),
            new Question("Which Chinese year follows the year of the sheep?", "Monkey", "Monkey", "Mouse", "Dog", "Tiger", 4, Question.Category.Misc),
            new Question("Vehicles from which country use the international registration letter C?", "Cuba", "Chile", "China", "Cuba", "Croatia", 5, Question.Category.Misc),
            new Question("Sam Phillips was owner of which legendary recording studio?", "Sun", "Mars", "Sun", "Moon", "Saturn", 6, Question.Category.Misc),
            new Question("In which year was CNN founded?", "1980", "1980", "1981", "1982", "1983", 7, Question.Category.Misc),
            new Question("In which country did General Jaruzelski impose marital law in 1981?", "Poland", "Ukraine", "Poland", "Turkey", "Sweden", 8, Question.Category.Misc),
            new Question("In which mountains are Camp David?", "Appalachian", "Karakoram", "Himalayas", "Andes", "Appalachian", 9, Question.Category.Misc),
            new Question("Who was the director of the CIA from 1976-81?", "George Bush", "William Colby", "George Bush", "Stansfield Turner", "Robert Gates", 10, Question.Category.Misc),


    };
    private static final Question[] QUESTIONS_HISTORY ={
            new Question("From which country did Singapore receive Independence?", "Malaysia", "France", "Malaysia", "Thailand", "United Kingdom", 1, Question.Category.History),
            new Question("What was the name of the Swiss satelite-state that was established in 1798 by the French?", "Helvetian Republic", "Baseler Republic", "Swiss Republic", "Chur Republic", "Helvetian Republic", 2, Question.Category.History),
            //new Question("Many scholars choose May 6, 1527, as an end point for the Renaissance. What event occurred on that date?", "The sack of Rome by soldiers of Charles V", "The sack of Rome by soldiers of Charles V", "The death of Dante Alighieri", "The death of Cesare Borgia", "The fall of the republic of Florence", 3, Question.Category.History),
            new Question("Which crusade founded the Latin empire in Constantinople?", "The 4th Crusade", "The 2nd Crusade", "The 3rd Crusade", "The 4th Crusade", "The 5th Crusade", 3, Question.Category.History),
            new Question("Which European nation briefly conquered Taiwan in the 17th century?", "The Netherlands", "The Netherlands", "Spain", "Portugal", "Britain", 4, Question.Category.History),
            new Question("Who brought the name Santa Claus with them to the New World?", "Dutch Colonists", "Welsh Colonists", "French Colonists", "Scandinavian Colonists", "Dutch Colonists", 5, Question.Category.History),
            new Question("What political coalition was elected in France in 1936?", "The Popular Front", "Labour Reform League", "The Social-Democrats", "The Popular Front", "The New Politics Coalition", 6, Question.Category.History),
            new Question("What was the last Republican city to fall to the Nationalist in the Spanish Civil War?", "Valencia", "Toledo", "Zaragoza", "Valencia", "Murcia", 7, Question.Category.History),
            new Question("What was the official faith of the last independent Yemenite Kingdom before the arrival of Islam?", "Judaism", "Judaism", "Nestorian Christianity", "Zoroastrianism", "Coptic Christianity", 8, Question.Category.History),
            new Question("Which Russian tzar was killed in his bedroom on March 11, 1801?", "Paul I", "Alexandre I", "Paul I", "Peter III", "Peter II", 9, Question.Category.History),
            new Question("Approximatively how many people died during the Swiss Sonderbundskrieg?", "100", "100", "1,000", "10,000", "100,000", 10, Question.Category.History),


    };
    private static final Question[] QUESTIONS_SPORTS ={
            new Question("Who ended Serena Williams' grand slam dream in 2015?", "Roberta Vinci", "Francesca Schiavone", "Roberta Vinci", "Svetlana Kuznetsova", "Flavia Pennetta", 1, Question.Category.Sports),
            new Question("What city does the NHL team the Sharks belong to?", "San Jose", "Arizona", "Montreal", "San Jose", "Tampa Bay", 2, Question.Category.Sports),
            //new Question("What was the name of the great steeplechaser who won the Cheltenham Gold Cup in 1964, 1965 and 1966?", "", "Desert Orchid", "Aldanati", "Arkle", "Nijinsky", 3, Question.Category.Sports),
            new Question("Which Briton was world motor racing champion in 1976 and retired three years later?", "Jackie Stewart", "James Hunt", "Graham Hill", "Jackie Stewart", "Bruce McLaren", 3, Question.Category.Sports),
            new Question("Which 6-a-side Olympic sport invented in 1895 by William Morgan was originally called Mintonette'?", "Volleyball", "Netball", "Croquet", "Volleyball", "Lacrosse", 4, Question.Category.Sports),
            new Question("Which cricketing county has been captained by Mike Atherton?", "Worcester", "Yorkshire", "Somerset", "Lancashire", "Worcester", 5, Question.Category.Sports),
            new Question("What nationality was 1962 Wimbledon champion Rod Laver?", "Canadian", "Canadian", "English", "American", "Australian", 6, Question.Category.Sports),
            new Question("Who led the major league in homeruns during the 2008 season?", "Alex Rodrigez", "Jim Tome", "Ryan Howard", "Derek Jeter", "Alex Rodrigez", 7, Question.Category.Sports),
            new Question("What team plays in the stadium that Ruth built?", "New York Yankees", "New York Yankees", "New York Mets", "Philadelphia Phillies", "Tampa Bay Rays", 8, Question.Category.Sports),
            new Question("Who won Superbowl XXIV?", "San Francisco 49ers", "Pittsburgh Steelers", "Dallas Cowboys", "New York Giants", "San Francisco 49ers", 9, Question.Category.Sports),
            new Question("Who led the Phillies in wins? As a pitcher.", "James Moyer", "James Moyer", "Cole Hamels", "Kyle Kendrick", "Brett Myers", 10, Question.Category.Sports),

    };
    private static final Quiz[] QUIZZES = {
            new Quiz("Geography Quiz", Quiz.Category.Geography, "Test your geographical knowledge.",QUESTIONS_GEOGRAPHY),
            new Quiz("Animal Quiz", Quiz.Category.Animals,"Test your knowledge of the Animal Kingdom.",QUESTIONS_ANIMALS),
            new Quiz("History Quiz", Quiz.Category.History,"Test your knowledge of human history.", QUESTIONS_HISTORY),
            new Quiz("Sports Quiz", Quiz.Category.Sports,"Test your sports IQ.", QUESTIONS_SPORTS),
            new Quiz("Random Trivia Quiz", Quiz.Category.Misc,"Random knowledge quiz.", QUESTIONS_MISC)


    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
