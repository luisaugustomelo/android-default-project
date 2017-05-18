package ni.com.debugon.notificationinfo.ni.com.debugon.DatabaseClasses;

/**
 * Created by luisr on 26/03/2017.
 */

public class Keyword {
    int _id;
    String _keyword;
    int _user;

    public Keyword(){}

    public Keyword(int id, String keyword, int user){
        this._id = id;
        this._keyword = keyword;
        this._user = user;
    }

    public void set_id(int id){
        this._id = id;
    }

    public int get_id() {
        return _id;
    }

    public String get_keyword() {
        return _keyword;
    }

    public void set_keyword(String _keyword) {
        this._keyword = _keyword;
    }

    public int get_user() {
        return _user;
    }

    public void set_user(int _user) {
        this._user = _user;
    }
}
