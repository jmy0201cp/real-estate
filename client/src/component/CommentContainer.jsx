import React, { useContext, useEffect, useState } from "react";
import { AiTwotoneEdit } from "react-icons/ai";
import CommentForm from "./CommentForm";
import Comment from "./Comment";
import { useParams } from "react-router-dom";
import { LoginTokenContext } from "../context/LoginTokenContext";
import httpFetch from "../network/http";

export default function InquiryComment() {
  const { roomId } = useParams();
  const [comments, setComments] = useState([]);
  const { token } = useContext(LoginTokenContext);

  useEffect(() => {
    async function fetchData() {
      const data = await httpFetch(`/rooms/${roomId}/comment`, {
        method: "GET",
      });
      setComments(data);
    }
    fetchData();
  }, [roomId]);

  return (
    <div className="rounded-lg shadow-md bg-white overflow-hidden cursor-pointer w-1/3 h-[365px] pt-2 border border-lightgray float-right p-2 mr-5 mb-5 items-center">
      <div>
        <div className="flex items-center justify-center">
          <AiTwotoneEdit />
          <p className="text-center pt-3 mb-4 ml-2 text-xs text-gray-400">
            해당 상품에 대한 문의를 남겨주세요.
          </p>
        </div>
        {!token && (
          <p className="text-[7px] text-right mr-2">
            댓글 기능은 로그인 후 사용 가능합니다
          </p>
        )}
      </div>
      <CommentForm />
      <ul className="my-2 w-full max-h-[255px] overflow-auto">
        {comments.map((comment) => (
          <Comment key={comment.commentId} comment={comment} />
        ))}
      </ul>
    </div>
  );
}
