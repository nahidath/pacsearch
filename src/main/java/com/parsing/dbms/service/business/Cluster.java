package com.parsing.dbms.service.business;

/**
 * Created bu PacLab
 * User: sami
 * Date: 2/9/2021
 * Time: 2:39 PM
 * Package: dbms.service.business
 * <p>
 * Class name: Map
 * Class description: A Map is a structured indexed representation of Terms and Documents.
 */
public class Cluster { // Table -> collection of tables (tables = documents) -> Cluster

    public Cluster() {

    }

    public void addDocument(Document documentToAdd) {

    }

    public void getDocumentId(String documentName) {

    }

    public void getDocumentName(String id) {

    }

    public void getTables() {

    }

    public void clearCluster() {

    }

    // TODO: implement Map logic
    // TODO: Cluster becomes a Page and Cluster class is the collection of pages /!\
    //List<Document> documentList; // list of documents
    //List<List<Pair<Integer, Integer>>> map; // content of cluster
    /*
    // representation of the map : (inverted search index)
                        -------------------------------------
    key[0] ->  (num_doc):(occ)  |(num_doc):(occ)  |
    key[1] ->  (num_doc):(occ)  |(num_doc):(occ)  |
                        --------------------------------------
                        + evalutation function
    What stands for what ? :
       num_doc : number of document
       occ : occurrence of the documentList[x] in the document of num_doc
  */

    // WARNING : this logic is implemented only in case no fields /!\

    // OTHER LOGIC :
    // Use Header Class
    // and normal two dimensional arrays

    // After V0 : see binary trees
}
