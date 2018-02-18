package pl.dogsinmylife.breed;


import pl.dogsinmylife.BasePresenter;
import pl.dogsinmylife.BaseView;

public interface BreedActivityContract {

    interface View extends BaseView<Presenter> {
        void progressView(boolean show);
        void showToastServerError();
        void showToastNoNetwork();
        void refreshTabs();
    }

    interface Presenter extends BasePresenter {
        void fetchData();
    }
}
