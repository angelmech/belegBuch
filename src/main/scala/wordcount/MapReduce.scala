package wordcount
import java.text.SimpleDateFormat
import java.util.Locale

object MapReduce :

  val dateFormatPattern= "yyyy.MM.dd G 'at' HH:mm:ss z"
  val sdf= new SimpleDateFormat(dateFormatPattern,Locale.ENGLISH)

  def mapReduceKV[S,B,R](mapFun:(S=>List[B]), redFun:(R,B)=>R, base:R, l:List[S]):R =
    l.flatMap(mapFun).
      foldLeft[R](base)(redFun)

  /*
    Write a function that determines how many jobs each user sumbmitted
    Result: Map (key:user, value: number)
   */
  def numberOfJobsPerUser(l:List[(String,String,String,Int)]):Map[String,Int]= ???

  /*
  Write a function that determines how many times a job name was used from each user
  Result: Map (key:(user,Job), value: number)
 */
  def numberOfJobsPerUserUsingACertainName(l:List[(String,String,String,Int)]):Map[(String,String),Int]= ???

  /*
    Write a function that determines all job names (without duplicates)
    Result: List(jobnames)
*/
  def distinctNamesOfJobs(l:List[(String,String,String,Int)]):List[String]= ???

  /*
    Write a function that determines how many jobs lasted more than 20sec
    Result: Map (key:("more" or "less"), value: number)
  */
  def moreThanXSeconds(l:List[(String,String,String,Int)]):Map[String,Int]= ???

  /*
    Write a function that determines the number of were submitted per day
    Result: Map (key:day- format "YYYY-MM-dd" , value: number)
  */
  def numberOfJobsPerDay(l:List[(String,String,String,Int)]):Map[String,Int]= ???

