package com.example.presentationassignment
import android.content.Intent
import com.example.presentationassignment.databinding.ActivityMain2Binding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import java.util.Stack
class MainActivity2 : AppCompatActivity() {
    private lateinit var binding:ActivityMain2Binding
    var from=0
    var to=0
    var trun_assigner=-1
    var top_jar_one= 3
    var top_jar_two= 3
    var top_jar_three= -1
    var apple_win=false
    var orange_win=false
    var jar1 = Stack<String>()
    var jar2 =  Stack<String>()
    var jar3 =  Stack<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        jar1.addAll(listOf("A", "O", "A", "O"))
        jar2.addAll(listOf("O","A", "O", "A"))
        move_at_screen()
        binding.firstjar.setOnClickListener {
                valid(1)
            }
        binding.secondjar.setOnClickListener{
            valid(2)
            }
        binding.thirdjar.setOnClickListener {
            valid(3)
        }
        binding.exit.setOnClickListener {
            System.exit(0)
        }
        binding.refresh.setOnClickListener {
            finish()
            startActivity(getIntent())
        }
        setContentView(binding.root)
    }
    fun move(from:Int,to:Int)
    {
        when(from){
            1->{
                when(to){
                    2->{
                        jar2.push(jar1.pop())
                        top_jar_one--
                        top_jar_two++
                    }
                    3->{
                        jar3.push(jar1.pop())
                        top_jar_one--
                        top_jar_three++
                    }
                    else->{
                        Toast.makeText(this,"Error Encountered 3",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            2->{
                when(to){
                    1->{
                        jar1.push(jar2.pop())
                        top_jar_one++
                        top_jar_two--
                    }
                    3->{
                        jar3.push(jar2.pop())
                        top_jar_three++
                        top_jar_two--
                    }
                    else->{
                        Toast.makeText(this,"Error Encountered 3",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            3->{
                when(to){
                1->{
                    jar1.push(jar3.pop())
                    top_jar_one++
                    top_jar_three--
                }
                2->{
                    jar2.push(jar3.pop())
                    top_jar_two++
                    top_jar_three--
                }
                else->{
                    Toast.makeText(this,"Error Encountered 3",Toast.LENGTH_SHORT).show()
                }
            }
            }
            else->{
                Toast.makeText(this,"Error Encountered 2",Toast.LENGTH_SHORT).show()
            }
        }
        move_at_screen()
    }
    fun move_at_screen()
    {
        var jar1image=arrayOf(binding.firstJarFour,binding.firstJarThree,binding.firstJarTwo,binding.firstJarOne)
        var jar2image=arrayOf(binding.secondJarFour,binding.secondJarThree,binding.secondJarTwo,binding.secondJarOne)
        var jar3image=arrayOf(binding.thirdJarFour,binding.thirdJarThree,binding.thirdJarTwo,binding.thirdJarOne)
        set_friuit_image(jar1,jar1image)
        set_friuit_image(jar2,jar2image)
        set_friuit_image(jar3,jar3image)
    }
    fun valid(jarnumber:Int)
    {
        trun_assigner++
        if(trun_assigner==0)
        {
            if(underflow_checker(jarnumber)) {
                from = jarnumber
                return
            }
            else
            {
                Toast.makeText(this,"Selected Jar is Empty",Toast.LENGTH_SHORT).show()
                trun_assigner--
            }
        }
        else if(trun_assigner==1)
        {
            to=jarnumber
            trun_assigner=-1
            if(from!=to)
            {
                if(overflow_checker(to))
                {
                    if(top_fruit_matcher(from,to)){
                move(from,to)
                check_for_win()
                    }
                    else
                    {
                        Toast.makeText(this,"Please Select Same Fruit",Toast.LENGTH_SHORT).show()
                    }
                }
                else
                {
                    Toast.makeText(this,"Jar is Already Full",Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(this,"Select Another Jar",Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun top_fruit_matcher(from:Int,to:Int): Boolean {
        if((to==1 && top_jar_one==-1)||(to==2 && top_jar_two==-1)||(to==3 && top_jar_three==-1))
        {
            return true
        }
        else if(from==1 && to==2 && jar1[top_jar_one]==jar2[top_jar_two])
        {
            return true
        }
        else if(from==1 && to==3 && jar1[top_jar_one]==jar3[top_jar_three])
        {
            return true
        }
        else if(from==2 && to==1 && jar2[top_jar_two]==jar1[top_jar_one])
        {
            return true
        }
        else if(from==2 && to==3 && jar2[top_jar_two]==jar3[top_jar_three])
        {
            return true
        }
        else if(from==3 && to==1 && jar1[top_jar_one]==jar3[top_jar_three])
        {
            return true
        }
        else if(from==3 && to==2 && jar3[top_jar_three]==jar2[top_jar_two])
        {
            return true
        }
        else
        {
            return false
        }
    }
    fun overflow_checker(num:Int): Boolean {
        if(num==1)
        {
            if(top_jar_one<3)
            {
                return true
            }
            else
            {
                return false
            }
        }
        else if(num==2)
        {
            if(top_jar_two<3)
            {
                return true
            }
            else
            {
                return false
            }
        }
        else if(num==3)
        {
            if(top_jar_three<3)
            {
                return true
            }
            else
            {
                return false
            }
        }
        else
        {
            Toast.makeText(this,"Problem Encountered 4",Toast.LENGTH_SHORT).show()
            return false
        }
    }
    fun underflow_checker(num:Int): Boolean {
        if(num==1)
        {
            if(top_jar_one!=-1)
            {
                return true
            }
            else
            {
                return false
            }
        }
        else if(num==2)
        {
            if(top_jar_two!=-1)
            {
                return true
            }
            else
            {
                return false
            }
        }
        else if(num==3)
        {
            if(top_jar_three!=-1)
            {
                return true
            }
            else
            {
                return false
            }
        }
        else
        {
            Toast.makeText(this,"Problem Encountered 4b",Toast.LENGTH_SHORT).show()
            return false
        }
    }
    fun set_friuit_image(arr: Stack<String>, arr_img:Array<ImageView>)
    {
        for (i in 0..arr.size-1) {
        if(arr[i].equals("O"))
        {
            arr_img[i].visibility=View.VISIBLE
            arr_img[i].setImageResource(R.drawable.orange)
        }
        else if(arr[i].equals("A"))
        {
            arr_img[i].visibility=View.VISIBLE
            arr_img[i].setImageResource(R.drawable.apple)
        }
        else
        {
            Toast.makeText(this,"Problem 1 occured",Toast.LENGTH_SHORT).show()
        }}
        if(arr.size<4)
        {
            for(i in arr.size..3) {
                    arr_img[i].visibility= View.INVISIBLE
                }
        }
    }
    fun check_for_win()
    {
        if((jar1.count { it == "A" } >= 4 || jar2.count { it == "A" } >= 4 || jar3.count { it == "A" } >= 4) && (jar1.count { it == "O" } >= 4 || jar2.count { it == "O" } >= 4 || jar3.count { it == "O" } >= 4))
        {
            val intent= Intent(this,MainActivity3::class.java)
            startActivity(intent)
            finish()
        }
    }
}