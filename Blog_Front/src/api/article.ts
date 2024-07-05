import { request } from '@/request';

interface ArticleData {
    code: number;
    msg: string;
    data: any;
}

interface CreateArticleDTO {
    userId: number;
    title: string;
    content: string;
    status: string;
    tagIds: number[];
}
// interface ArticlePageSearchDTO {
//     page:number,
//     pageSize:number
// }
export function createArticle(articleDTO: CreateArticleDTO) {
    return request<ArticleData>({
        url: '/api/articles/create',
        method: 'post',
        data: {
            userId: articleDTO.userId,
            title: articleDTO.title,
            content: articleDTO.content,
            status: articleDTO.status,
            tagIds: articleDTO.tagIds,
        },
        headers: {
            'Content-Type': 'application/json',
        },
    });
}
export function getArticlesByPage(page:number,pageSize:number) {
    return request({
        url: '/api/articles/page',
        method: 'get',
        // data: {
        //     page: articleDTO.page,
        //     pageSize: articleDTO.pageSize,
        // },
    });
}
