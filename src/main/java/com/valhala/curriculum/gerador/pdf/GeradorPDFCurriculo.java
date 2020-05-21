package com.valhala.curriculum.gerador.pdf;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.valhala.curriculum.model.Curriculo;
import com.valhala.curriculum.model.DadosPessoais;
import com.valhala.curriculum.model.ExperienciaProfissional;
import com.valhala.curriculum.model.FormacaoAcademica;

public class GeradorPDFCurriculo {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	private static final Font FONT_TITULO = new Font(FontFamily.HELVETICA, 20, Font.BOLD);
	private static final Font FONT_INFORMACOES = new Font(FontFamily.HELVETICA, 10);
	private static final Font FONT_SUB_SECAO = new Font(FontFamily.HELVETICA, 14, Font.BOLD);

	public static final byte[] geraPdf(DadosPessoais dadosPessoais, Curriculo curriculo)
			throws DocumentException, FileNotFoundException {

		ByteArrayOutputStream output = new ByteArrayOutputStream();

		Document documento = new Document();

		PdfWriter.getInstance(documento, output);

		documento.open();

		insereTituloCurriculo(dadosPessoais, documento);

		insereLinhaEmBranco(documento);

		incluiDadosPessoais(dadosPessoais, documento);

		insereLinhaEmBranco(documento);

		if (curriculo != null && curriculo.getFormacoesAcademicas() != null
				&& !curriculo.getFormacoesAcademicas().isEmpty()) {
			insereFormacoesAcademicas(curriculo, documento);
		}

		insereLinhaEmBranco(documento);

		if (curriculo != null && curriculo.getExperienciasProfissionais() != null
				&& !curriculo.getExperienciasProfissionais().isEmpty()) {
			insereExperienciasProfissionais(curriculo, documento);
		}

		documento.close();

		return output.toByteArray();

	}

	private static void insereExperienciasProfissionais(Curriculo curriculo, Document documento)
			throws DocumentException {
		final Paragraph formacoes = new Paragraph("Experiências Profissionais", FONT_SUB_SECAO);
		documento.add(formacoes);

		for (Iterator<ExperienciaProfissional> iterator = curriculo.getExperienciasProfissionais().iterator(); iterator
				.hasNext();) {
			ExperienciaProfissional experiencia = iterator.next();
			final Paragraph empresa = new Paragraph("Empresa: " + experiencia.getEmpresa().getNome(), FONT_INFORMACOES);
			final Paragraph cargo = new Paragraph("Cargo: " + experiencia.getCargo().getNome(), FONT_INFORMACOES);
			final Paragraph inicio = new Paragraph("Início: " + DATE_FORMAT.format(experiencia.getDataInicio()),
					FONT_INFORMACOES);
			final Paragraph termino = new Paragraph("Saída: " + DATE_FORMAT.format(experiencia.getDataSaida()),
					FONT_INFORMACOES);
			documento.add(empresa);
			documento.add(cargo);
			documento.add(inicio);
			documento.add(termino);

			if (iterator.hasNext()) {
				insereLinhaEmBranco(documento);
			}

		}
	}

	private static void insereTituloCurriculo(DadosPessoais dadosPessoais, Document documento)
			throws DocumentException {
		Paragraph nome = new Paragraph(dadosPessoais.getNomeInteiro(), FONT_TITULO);
		nome.setAlignment(Element.ALIGN_CENTER);
		documento.add(nome);

		Paragraph titulo = new Paragraph(dadosPessoais.getTitulo());
		titulo.setAlignment(Element.ALIGN_CENTER);
		documento.add(titulo);
	}

	private static void insereFormacoesAcademicas(Curriculo curriculo, Document documento) throws DocumentException {
		final Paragraph formacoes = new Paragraph("Formações Acadêmicas", FONT_SUB_SECAO);
		documento.add(formacoes);

		for (Iterator<FormacaoAcademica> iterator = curriculo.getFormacoesAcademicas().iterator(); iterator
				.hasNext();) {
			FormacaoAcademica formacao = iterator.next();

			final Paragraph entidadeEnsino = new Paragraph(
					"Entidade de Ensino: " + formacao.getEntidadeEnsino().getNome(), FONT_INFORMACOES);
			final Paragraph curso = new Paragraph("Curso: " + formacao.getCurso().getNome(), FONT_INFORMACOES);
			final Paragraph tipoCurso = new Paragraph("Tipo de Curso: " + formacao.getTipoFormacao().getDescricao(),
					FONT_INFORMACOES);
			final Paragraph inicio = new Paragraph("Início: " + DATE_FORMAT.format(formacao.getDataInicio()),
					FONT_INFORMACOES);
			final Paragraph termino = new Paragraph("Término: " + DATE_FORMAT.format(formacao.getDataTermino()),
					FONT_INFORMACOES);
			documento.add(entidadeEnsino);
			documento.add(curso);
			documento.add(tipoCurso);
			documento.add(inicio);
			documento.add(termino);

			if (iterator.hasNext()) {
				insereLinhaEmBranco(documento);
			}

		}

	}

	private static void insereLinhaEmBranco(Document documento) throws DocumentException {
		documento.add(new Paragraph(" "));
	}

	private static void incluiDadosPessoais(DadosPessoais dadosPessoais, Document documento) throws DocumentException {
		final Paragraph dados = new Paragraph("Dados Pessoais", FONT_SUB_SECAO);
		final Paragraph dataNascimento = new Paragraph(
				"Data de Nascimento: " + DATE_FORMAT.format(dadosPessoais.getDataNascimento()), FONT_INFORMACOES);
		final Paragraph email = new Paragraph("E-mail: " + dadosPessoais.getEmail(), FONT_INFORMACOES);
		final Paragraph telefone = new Paragraph("Telefone: " + dadosPessoais.getTelefone(), FONT_INFORMACOES);
		final Paragraph endereco = new Paragraph("Endereço: " + dadosPessoais.getEndereco().completo(),
				FONT_INFORMACOES);

		documento.add(dados);
		documento.add(dataNascimento);
		documento.add(telefone);
		documento.add(email);
		documento.add(endereco);
	}

}
