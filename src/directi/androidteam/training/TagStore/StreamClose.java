package directi.androidteam.training.TagStore;

/**
 * Created with IntelliJ IDEA.
 * User: vinayak
 * Date: 9/10/12
 * Time: 5:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class StreamClose extends Tag {
    public StreamClose(){
        tagname = "stream:stream";
        tagId = "streamclose";
    }

    @Override
    public String toXml(){
        return "</"+tagname+">";
    }
}
