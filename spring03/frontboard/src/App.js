import './App.css';

import 'bootstrap/dist/css/bootstrap.min.css';

// 화면 하루팅을 위해서 라이브러리 추가
import { Routes,Route } from 'react-router-dom';
import React from 'react';

// 만든 화면 추가
import Home from './routes/Home';
import BoardList from './routes/BoardList';
import QnaList from './routes/QnaList';
import Login from './routes/Login';
import BoardDetail from './routes/BoardDetail'; // 게시글 상세 추가


function App() {
  return (
    <Routes>
      {/* a, Link 링크를 누르면 화전전환될 페이지 */}
      <Route path='/home' element={<Home/>}/>
      <Route path='/BoardList' element={<BoardList/>}/>
      <Route path='/boardDetail/:bno' element={<BoardDetail/>}/>
      <Route path='/QnaList' element={<QnaList/>}/>
      <Route path='/Login' element={<Login/>}/>

    </Routes>
  );
}

export default App;
