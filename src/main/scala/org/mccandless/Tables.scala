package org.mccandless
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.MySQLProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Cats.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Cats
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(128,true)
   *  @param breed Database column breed SqlType(VARCHAR), Length(128,true) */
  final case class CatsRow(id: Int, name: String, breed: String)
  /** GetResult implicit for fetching CatsRow objects using plain SQL queries */
  implicit def GetResultCatsRow(implicit e0: GR[Int], e1: GR[String]): GR[CatsRow] = GR{
    prs => import prs._
    CatsRow.tupled((<<[Int], <<[String], <<[String]))
  }
  /** Table description of table cats. Objects of this class serve as prototypes for rows in queries. */
  class Cats(_tableTag: Tag) extends profile.api.Table[CatsRow](_tableTag, Some("slick"), "cats") {
    def * = (id, name, breed) <> (CatsRow.tupled, CatsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(name), Rep.Some(breed)).shaped.<>({r=>import r._; _1.map(_=> CatsRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(128,true) */
    val name: Rep[String] = column[String]("name", O.Length(128,varying=true))
    /** Database column breed SqlType(VARCHAR), Length(128,true) */
    val breed: Rep[String] = column[String]("breed", O.Length(128,varying=true))
  }
  /** Collection-like TableQuery object for table Cats */
  lazy val Cats = new TableQuery(tag => new Cats(tag))
}
