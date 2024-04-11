package com.example.anavnaigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class EquipmentDetail : Fragment() {
    private lateinit var Name:TextView
    private lateinit var quantity:TextView
    private lateinit var photo:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_equipment_detail, container, false)
        Name=view.findViewById(R.id.EquipmentName)
        quantity=view.findViewById(R.id.EquipmentQuantity)
        photo=view.findViewById(R.id.EquipmentImage)

        val equipmentName = arguments?.getString(MyAdapter.EQUIPMENT_ITEM_KEY1)
        val equipmentQuantity = arguments?.getString(MyAdapter.EQUIPMENT_ITEM_KEY2)
        val equipmentPhoto = arguments?.getString(MyAdapter.EQUIPMENT_ITEM_KEY3)
        Name.text=equipmentName
        photo.setImageResource(equipmentPhoto!!.toInt())
        if(equipmentName=="Badminton Racket") {
            quantity.text = "Badminton is a popular racquet sport played by either two opposing players (singles) or two pairs of players (doubles). The game is played indoors on a rectangular court divided by a net, with players using a shuttlecock to score points.\nBadminton is played at the professional level in international tournaments such as the All England Open Badminton Championships, the BWF World Championships, and the Olympic Games.\n" +
                    "\n" +
                    "Badminton is a sport that combines speed, skill, and tactics, making it both physically demanding and intellectually engaging. It is a popular recreational activity and a highly competitive sport played by millions of people worldwide."
        }
        if(equipmentName=="Basket Ball") {
            quantity.text = "Basketball is a fast-paced team sport played on a rectangular court with two teams of five players each. The objective is to score points by shooting a ball through the opponent's hoop, which is mounted 10 feet (3.05 meters) high on a backboard.\nThe National Basketball Association (NBA) in the United States is one of the most prominent professional basketball leagues globally. Other countries also have their professional leagues.\n" +
                    "\n" +
                    "Basketball is known for its dynamic and fast-paced nature, requiring a combination of physical fitness, skill, and teamwork. It is played and enjoyed by millions of people around the world, both recreationally and competitively."
        }
        if(equipmentName=="Carromboard") {
            quantity.text = "Carrom is a popular indoor board game that originated in India but has gained international popularity. It is played by two to four players, either in singles or doubles. The game involves using a striker to pocket the carrom men (playing pieces) into corner pockets on a square wooden board.\nCarrom is a game that combines elements of strategy, skill, and precision. It is often enjoyed in social settings and is played competitively at various levels. The simplicity of the game, coupled with its potential for tactical depth, makes carrom a popular choice for both casual and serious gamers."
        }
        if(equipmentName=="Chessboard") {
            quantity.text = "Chess is a strategic two-player board game that has been played for centuries. It is widely considered one of the most intellectually challenging and sophisticated games. \n Chess has a vast array of openings and strategies, with names like the Sicilian Defense, Queen's Gambit, and others. Players often study and develop preferences for specific openings.\n" +
                    "\n" +
                    "Chess is not just a game; it's a mental sport that demands strategic thinking, planning, and adaptability. It has a rich history and has been a source of inspiration for many, emphasizing the importance of intelligence, foresight, and skill in its pursuit."
        }
        if(equipmentName=="Cricket Ball") {
            quantity.text = "A cricket ball is a critical piece of equipment used in the sport of cricket. It is a hard, round object that plays a central role in the game, being bowled by the bowler and struck by the batsman. \n Different brands produce cricket balls used in various cricket-playing nations. Dukes balls are commonly used in England, Kookaburra balls in Australia and many other countries, and SG balls in India.\n" +
                    "\n" +
                    "The cricket ball's characteristics can have a significant impact on the strategies employed by bowlers and batsmen during a match. The way it swings, seams, or behaves off the pitch can be crucial in determining the outcome of a game, making it an essential component of the sport."
        }
        if(equipmentName=="Cricket Bat") {
            quantity.text = "A cricket bat is a specialized piece of equipment used in the sport of cricket to hit the ball bowled by the opposing team's bowler.\nBefore using a new cricket bat in a match, it needs to be \"knocked-in.\" This process involves gently striking the surface with a specialized mallet to compress the wood fibers and improve the bat's performance.\n" +
                    "Choosing the right cricket bat is a personal preference, and players often select a bat based on their playing style, the type of pitches they encounter, and their level of experience. A well-chosen and properly maintained cricket bat can significantly impact a batsman's performance on the field."
        }
        if(equipmentName=="Football") {
            quantity.text = "Football, known as soccer in some countries, is the world's most popular sport, played and watched by millions of people around the globe.\nPlayers participate in both club and national team competitions. Club football involves domestic and international leagues, while national teams compete in regional and global tournaments.\n" +
                    "Football's simplicity, accessibility, and universal appeal contribute to its status as the \"beautiful game.\" The sport brings people together, evokes intense emotions, and provides thrilling moments of skill and athleticism on the pitch."
        }
        if(equipmentName=="Table Tennis Racket") {
            quantity.text = "A table tennis racket, also known as a table tennis paddle or bat, is an essential piece of equipment in the sport of table tennis .\nChoosing the right table tennis racket is a personal decision, influenced by playing style, skill level, and preferences. Beginners may start with a pre-made racket, while advanced players often assemble custom setups to optimize performance. Regular practice and experimentation with different racket configurations can help players find the ideal combination for their table tennis game."
        }
        if(equipmentName=="Tennis Racket") {
            quantity.text =
                    "A tennis racket is a crucial piece of equipment in the sport of tennis, and it plays a significant role in a player's performance. \nAdvancements in racket technology include features like vibration dampening systems, aerodynamic designs, and new materials to enhance performance. Players often choose rackets with technologies that complement their playing style."
        }
        if(equipmentName=="Volleyball") {
            quantity.text = "Volleyball is a dynamic and fast-paced team sport played by two teams,each consisting of six players. The primary objective is to score points by grounding the ball on the opposing team's side of the court. Volleyball is not only a thrilling sport to watch but also demands a unique combination of athleticism, strategy, and teamwork from its players."
        }



        return view

    }

}