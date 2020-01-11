package net.tiny.feature.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import net.tiny.dao.entity.BaseEntity;

/**
 * Article - 文章
 *
 */
@Entity
@Table(name = "article")
public class Article extends BaseEntity {

    @Transient
    private static final long serialVersionUID = 1L;

    /** 点击数缓存名称 */
    public static final String HITS_CACHE_NAME = "articleHits";

    /** 点击数缓存更新间隔时间 */
    public static final int HITS_CACHE_INTERVAL = 600000;

    /** 内容分页长度 */
    private static final int PAGE_CONTENT_LENGTH = 800;

    /** 内容分页符 */
    private static final String PAGE_BREAK_SEPARATOR = "<hr class=\"pageBreak\" />";

    /** 段落分隔符配比 */
    private static final Pattern PARAGRAPH_SEPARATOR_PATTERN = Pattern.compile("[,;\\.!?，；。！？]");

    /** 静态路径 */
    private static String staticPath;

    /** ID */
    @SequenceGenerator(name = "articleSequenceGenerator", sequenceName = "article_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "articleSequenceGenerator")
    @Id
    @Column(name = "id")
    private Long id;

    /** 标题 */
    @Column(nullable = false, length=200)
    private String title;

    /** 作者 */
    @Column(length=200)
    private String author;

    /** 内容 */
    @Lob
    private String content;

    /** 页面标题 */
    @Column(name = "seo_title", length = 200)
    private String seoTitle;

    /** 页面关键词 */
    @Column(length = 200)
    private String keywords;

    /** 页面描述 */
    @Column(length=200)
    private String description;

    /** 是否发布 */
    @NotNull
    @Column(nullable = false, length=200)
    private Boolean isPublication;

    /** 是否置顶 */
    @NotNull
    @Column(nullable = false)
    private boolean isTop;

    /** 点击数 */
    @Column(name = "hits", nullable = false)
    private Long hits;

    /** 页码 */
    @Transient
    private Integer pageNumber;

    /** 文章分类 */
    @NotNull
    @ManyToOne(cascade ={ CascadeType.REMOVE })
    @JoinColumn(name = "article_category", referencedColumnName = "id", nullable = false)
    private ArticleCategory articleCategory;

    /** 标签 */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "article_tag"
    , joinColumns = {@JoinColumn(name = "articles", referencedColumnName="id")}
    , inverseJoinColumns = {@JoinColumn(name = "tags", referencedColumnName="id")}
    )
    @OrderBy("order asc")
    private Set<Tag> tags = new HashSet<Tag>();

    /** 文章主题 */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "article_thema"
    , joinColumns = {@JoinColumn(name = "articles", referencedColumnName="id")}
    , inverseJoinColumns = {@JoinColumn(name = "thema", referencedColumnName="id")}
    )
    @OrderBy("order asc")
    private Set<Thema> themas = new HashSet<Thema>();

    static {
        try {
            /*
            File shopxxXmlFile = new ClassPathResource(CommonAttributes.SHOPXX_XML_PATH).getFile();
            org.dom4j.Document document = new SAXReader().read(shopxxXmlFile);
            org.dom4j.Element element = (org.dom4j.Element) document.selectSingleNode("/shopxx/template[@id='articleContent']");
            staticPath = element.attributeValue("staticPath");
            */
            //FIXME
            staticPath = "/article/content/${createDate?string('yyyyMM')}/${id}/${pageNumber!1}.html";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取ID
     *
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id
     *            ID
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * 获取标题
     *
     * @return 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title
     *            标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取作者
     *
     * @return 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author
     *            作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取内容
     *
     * @return 内容
     */
    public String getContent() {
        if (pageNumber != null) {
            String[] pageContents = getPageContents();
            if (pageNumber < 1) {
                pageNumber = 1;
            }
            if (pageNumber > pageContents.length) {
                pageNumber = pageContents.length;
            }
            return pageContents[pageNumber - 1];
        } else {
            return content;
        }
    }

    /**
     * 设置内容
     *
     * @param content
     *            内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取页面标题
     *
     * @return 页面标题
     */
    public String getSeoTitle() {
        return seoTitle;
    }

    /**
     * 设置页面标题
     *
     * @param seoTitle
     *            页面标题
     */
    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    /**
     * 获取页面关键词
     *
     * @return 页面关键词
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * 设置页面关键词
     *
     * @param keywords
     *            页面关键词
     */
    public void setKeywords(String keywords) {
        if (keywords != null) {
            this.keywords = keywords.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "");
        }
    }

    /**
     * 获取页面描述
     *
     * @return 页面描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置页面描述
     *
     * @param seoDescription
     *            页面描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取是否发布
     *
     * @return 是否发布
     */
    public Boolean getIsPublication() {
        return isPublication;
    }

    /**
     * 设置是否发布
     *
     * @param publication
     *            是否发布
     */
    public void setIsPublication(Boolean publication) {
        this.isPublication = publication;
    }

    /**
     * 获取是否置顶
     *
     * @return 是否置顶
     */
    public boolean getIsTop() {
        return isTop;
    }

    /**
     * 设置是否置顶
     *
     * @param isTop
     *            是否置顶
     */
    public void setIsTop(boolean top) {
        this.isTop = top;
    }

    /**
     * 获取点击数
     *
     * @return 点击数
     */
    public Long getHits() {
        return hits;
    }

    /**
     * 设置点击数
     *
     * @param hits
     *            点击数
     */
    public void setHits(Long hits) {
        this.hits = hits;
    }

    /**
     * 获取页码
     *
     * @return 页码
     */
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * 设置页码
     *
     * @param pageNumber
     *            页码
     */
    @Transient
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * 获取文章分类
     *
     * @return 文章分类
     */
    public ArticleCategory getArticleCategory() {
        return articleCategory;
    }

    /**
     * 设置文章分类
     *
     * @param articleCategory
     *            文章分类
     */
    public void setArticleCategory(ArticleCategory articleCategory) {
        this.articleCategory = articleCategory;
    }

    /**
     * 获取标签
     *
     * @return 标签
     */
    public Set<Tag> getTags() {
        return tags;
    }

    /**
     * 设置标签
     *
     * @param tags
     *            标签
     */
    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    /**
     * 获取访问路径
     *
     * @return 访问路径
     */
    @Transient
    public String getPath() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", getId());
        model.put("createDate", getCreateDate());
        model.put("modifyDate", getModifyDate());
        model.put("title", getTitle());
        model.put("seoTitle", getSeoTitle());
        model.put("keywords", getKeywords());
        model.put("description", getDescription());
        model.put("pageNumber", getPageNumber());
        model.put("articleCategory", getArticleCategory());
        return staticPath; //FIXME
    }

    /**
     * 获取文本内容
     *
     * @return 文本内容
     */
    @Transient
    public String getText() {
        final String text = getContent();
        return text;
    }

    /**
     * 获取分页内容
     *
     * @return 分页内容
     */
    @Transient
    public String[] getPageContents() {
        if (null == content || content.isEmpty()) {
            return new String[] { "" };
        }
        return new String[] { "" };//FIXME
        /*
        if (content.contains(PAGE_BREAK_SEPARATOR)) {
            return content.split(PAGE_BREAK_SEPARATOR);
        } else {
            List<String> pageContents = new ArrayList<String>();
            Document document = Jsoup.parse(content);
            List<Node> children = document.body().childNodes();
            if (children != null) {
                int textLength = 0;
                StringBuffer html = new StringBuffer();
                for (Node node : children) {
                    if (node instanceof Element) {
                        Element element = (Element) node;
                        html.append(element.outerHtml());
                        textLength += element.text().length();
                        if (textLength >= PAGE_CONTENT_LENGTH) {
                            pageContents.add(html.toString());
                            textLength = 0;
                            html.setLength(0);
                        }
                    } else if (node instanceof TextNode) {
                        TextNode textNode = (TextNode) node;
                        String text = textNode.text();
                        String[] contents = PARAGRAPH_SEPARATOR_PATTERN.split(text);
                        Matcher matcher = PARAGRAPH_SEPARATOR_PATTERN.matcher(text);
                        for (String content : contents) {
                            if (matcher.find()) {
                                content += matcher.group();
                            }
                            html.append(content);
                            textLength += content.length();
                            if (textLength >= PAGE_CONTENT_LENGTH) {
                                pageContents.add(html.toString());
                                textLength = 0;
                                html.setLength(0);
                            }
                        }
                    }
                }
                String pageContent = html.toString();
                if (pageContent != null && !pageContent.isEmpty()) {
                    pageContents.add(pageContent);
                }
            }
            return pageContents.toArray(new String[pageContents.size()]);
        }
        */
    }

    /**
     * 获取总页数
     *
     * @return 总页数
     */
    @Transient
    public int getTotalPages() {
        return getPageContents().length;
    }

}
