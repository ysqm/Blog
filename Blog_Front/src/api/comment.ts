import { request} from '@/request';

interface UserData {
    code: number;
    msg: string;
    data: any;
    // 其他属性
}

interface CommentDTO {

    userId: string;
    commentId: string;
    content: string;
    articleId: string;
}

export function addComment(username:string,password:string){
    return request<UserData>({
        url:'/api/comment/add',
        method: "post",
        data:{

        }
    })
}

export function deleteComment(CommentDTO:CommentDTO){
    return request<UserData>(
        {
            url:'/api/comment/delete/{commentId}',
            method: "post",
            data: CommentDTO,
        }
    )
}