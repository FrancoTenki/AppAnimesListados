package com.example.appanimes.entidades;

public class Anime {
    public int mal_id;
    public String title;
    public int episodes;
    public ImageUrls images;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEpisodes() {
        return episodes;
    }

    public int getMal_id() {
        return mal_id;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public ImageUrls getImages() {
        return images;
    }

    public void setImages(ImageUrls images) {
        this.images = images;
    }


    public static class ImageUrls {
        private JpgImages jpg;

        public JpgImages getJpg() {
            return jpg;
        }

        public void setJpg(JpgImages jpg) {
            this.jpg = jpg;
        }


        public static class JpgImages {
            private String imageUrl;

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }
        }
    }
}
