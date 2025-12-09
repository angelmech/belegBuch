package wordcount

class Processing :
   
  /**********************************************************************************************
   *
   *                          Aufgabe 1
   *
   *  Gruppenarbeit: Angel Mechkarov, Kerem Gürbüz
   *********************************************************************************************
  */
  def getWords(line:String):List[String]=
    /*
     * Extracts all words from a line
     * 
     * 1. Removes all characters which are not letters (A-Z or a-z)
     * 2. Shifts all words to lower case
     * 3. Extracts all words and put them into a list of strings
     */
    val lower= line.toLowerCase.replaceAll("[^a-z]", " ")
    lower.split(" ").filter(_!="").toList

  
  def getAllWords(l:List[(Int,String)]):List[String]=
    /*
     * Extracts all words from a List containing line number and line tuples
     * The words should be in the same order as they occur in the source document
     * 
     * Hint: Use the flatMap function
     */
     l.flatMap(x => getWords(x._2))
  
  def countWords(l:List[String]):List[(String,Int)]=
    /*
     *  Gets a list of words and counts the occurrences of the individual words
     */
    l.foldLeft(Map[String,Int]())((m,x) => m.updated(x, 1 + m.getOrElse(x,0))).toList
  
  /**********************************************************************************************
   *
   *                          Aufgabe 2
   *
   *   Gruppenarbeit: Angel Mechkarov, Kerem Gürbüz
   *********************************************************************************************
  */

  def getAllWordsWithIndex(l: List[(Int, String)]): List[(Int, String)] =
    l.flatMap(x => getWords(x._2).map(y => (x._1, y)))

  def createInverseIndex(l: List[(Int, String)]): Map[String, List[Int]] =
    getAllWordsWithIndex(l).groupBy(_._2).map(x => (x._1, x._2.map(_._1)))

  def orConjunction(words: List[String], invInd: Map[String, List[Int]]): List[Int] =
    words.flatMap(x => invInd.getOrElse(x, Nil))

  def andConjunction(words: List[String], invInd: Map[String, List[Int]]): List[Int] =
    words.map(x => invInd.getOrElse(x, Nil)).reduce((a, b) => a.intersect(b))



object Processing {

  def getData(filename: String): List[(Int, String)] = {
    val url = getClass.getResource("/" + filename).getPath
    val src = scala.io.Source.fromFile(url)
    val iter = src.getLines()
    var c = -1
    val result = (for (row <- iter) yield {
      c = c + 1; (c, row)
    }).toList
    src.close()
    result
  }
}