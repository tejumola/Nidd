package pinnacle.org.nidd.model;

import java.util.Date;

import pinnacle.org.nidd.utils.Builder;
import pinnacle.org.nidd.utils.BuilderBuild;


/**
 * @author DOTECH
 */
 public  final class Chat<T > {

    private String id;
    private final T owner;
    private final String lastMessage;
    private final Date date;

    private Chat(T owner,String lastMessage){
        this(new PostBuilder<T>(owner,lastMessage,new Date()));
    }

    /**
     * Default constructor
     */
    private Chat(PostBuilder<T> postBuilder) {
        this.owner = postBuilder.owner;
        this.lastMessage = postBuilder.lastMessage;
        this.date=postBuilder.date;
    }


    /**
     * This method returns the given id of the user entity
     *
     * @return id of the   entity
     */
    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id= id;
    }

    /**
     * @return the post
     */
    public String getLastMessage() {
        return lastMessage;
    }

    /**
     * @return the date of the post
     */
    public Date getDate() {
        return date;
    }

    /**
     * Please note call @code{#getPostOwnerType}  to get the owner type so as to cast effectively
     *
     * @return the owner.
     */
    public T getOwner() {
        return owner;
    }


    /**
     * get the content of the post, may be an image or video
     * @return
     */
    public byte[] getVisitorAvatar(){

        return this.getVisitorAvatar();
    }

    @Override
    public String toString() {
        return lastMessage;
    }

    /**
     * <p>
     * This is a Builder Class for the Post. it implements two build methods.
     *
     * @param <T> the Owner Type
     * @code #build #build(Builder<? extends B>, R), where R is the return type of Post object and B is the builder instance</p>
     */
    public static class PostBuilder<T> implements Builder<Chat>, BuilderBuild<Builder<Chat>, Chat> {
        private T owner;
        private String visitorName;
        private String lastMessage;
        private byte[] visitorAvatar;
        private Date date;

        public PostBuilder(T owner,String lastMessage, Date date) {
            setOwner(owner);
            setLastMessage(lastMessage);
            setDate(date);
        }

        /**
         * @param owner the owner of the post
         */
        private PostBuilder setOwner(T owner) {
            this.owner = owner;
            return this;
        }



        /**
         * @param date the date of the post to be set
         */
        private PostBuilder setDate(Date date) {
            this.date = date;
            return this;
        }


        /**
         * @param lastMessage the postComments to be set
         */
        public PostBuilder setLastMessage(String lastMessage) {
            this.lastMessage = lastMessage;
            return this;
        }


        @Override
        public Chat build(Chat type) {
            return null;
        }

        @Override
        public Chat build() {
            return new Chat(this);
        }

        /**
         * build with a builder
         *
         * @param builder
         * @return
         */
        @Override
        public Chat build(Builder<Chat> builder) {
            return new Chat((PostBuilder) builder);

        }
    }

}