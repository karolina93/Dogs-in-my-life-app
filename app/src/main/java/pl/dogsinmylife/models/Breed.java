package pl.dogsinmylife.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import pl.dogsinmylife.database.DB;


@Table(database = DB.class)
public class Breed extends BaseModel {

    @PrimaryKey(autoincrement = true)
    int id;

    @Column
    String name;

    @Column
    boolean liked;

    public Breed(String name) {
        this.name = name;
    }

    public Breed() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
