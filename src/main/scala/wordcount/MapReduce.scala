package wordcount
import java.text.SimpleDateFormat
import java.util.Locale

object MapReduce :

  val dateFormatPattern= "yyyy.MM.dd G 'at' HH:mm:ss z"
  val sdf= new SimpleDateFormat(dateFormatPattern,Locale.ENGLISH)

  def mapReduceKV[S,B,R](mapFun:(S=>List[B]), redFun:(R,B)=>R, base:R, l:List[S]):R =
    l.flatMap(mapFun).
      foldLeft[R](base)(redFun)

  //********************************************
  //Gruppenarbeit: Angel Mechkarov, Kerem Gürbüz
  //********************************************

  /*
    Write a function that determines how many jobs each user sumbmitted
    Result: Map (key:user, value: number)
   */
  def numberOfJobsPerUser(l:List[(String,String,String,Int)]):Map[String,Int]=
    mapReduceKV[(String,String,String,Int), (String,Int), Map[String,Int]](
      x => List((x._2, 1)),
      (m,x) => m.updated(x._1, x._2 + m.getOrElse(x._1, 0)),
      Map[String,Int](),
      l
    )

  /*
  Write a function that determines how many times a job name was used from each user
  Result: Map (key:(user,Job), value: number)
 */
  def numberOfJobsPerUserUsingACertainName(l:List[(String,String,String,Int)]):Map[(String,String),Int]=
    mapReduceKV[(String,String,String,Int), ((String,String), Int), Map[(String, String), Int]](
      x => List(((x._2, x._3), 1)),
      (m,x) => m.updated((x._1._1, x._1._2), x._2 + m.getOrElse((x._1._1, x._1._2), 0)),
      Map[(String, String), Int](),
      l
    )

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

